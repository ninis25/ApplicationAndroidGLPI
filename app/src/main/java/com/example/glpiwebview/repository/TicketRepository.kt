package com.example.glpiwebview.repository

import android.content.Context
import android.util.Log
import com.example.glpiwebview.PreferenceManager
import com.example.glpiwebview.api.GlpiApiService
import com.example.glpiwebview.api.LoginRequest
import com.example.glpiwebview.api.Ticket
import com.example.glpiwebview.api.TicketEntity
import com.example.glpiwebview.db.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class TicketRepository(private val context: Context) {
    
    private val preferenceManager = PreferenceManager(context)
    private val ticketDao = AppDatabase.getDatabase(context).ticketDao()
    private var apiService: GlpiApiService? = null
    
    fun hasValidCredentials(): Boolean {
        return preferenceManager.hasValidApiCredentials() && preferenceManager.hasValidSession()
    }
    
    private fun getApiService(): GlpiApiService {
        if (apiService == null) {
            apiService = GlpiApiService.create(preferenceManager.getApiBaseUrl())
        }
        return apiService!!
    }
    
    suspend fun login(username: String, password: String): Result<Boolean> {
        return withContext(Dispatchers.IO) {
            try {
                val response = getApiService().login(
                    loginRequest = LoginRequest(username, password)
                )
                
                if (response.isSuccessful && response.body() != null) {
                    val sessionData = response.body()!!
                    preferenceManager.saveSessionInfo(sessionData.sessionToken, sessionData.userId)
                    preferenceManager.saveApiCredentials(username, password)
                    Result.success(true)
                } else {
                    Result.failure(Exception("Login failed: ${response.code()} ${response.message()}"))
                }
            } catch (e: IOException) {
                Result.failure(Exception("Network error: ${e.message}"))
            } catch (e: HttpException) {
                Result.failure(Exception("HTTP error: ${e.message}"))
            } catch (e: Exception) {
                Result.failure(Exception("Unknown error: ${e.message}"))
            }
        }
    }
    
    suspend fun refreshTickets(): Result<List<Ticket>> {
        return withContext(Dispatchers.IO) {
            try {
                if (!preferenceManager.hasValidSession()) {
                    // Try to login again if session is invalid
                    if (preferenceManager.hasValidApiCredentials()) {
                        val loginResult = login(
                            preferenceManager.glpiUsername,
                            preferenceManager.glpiPassword
                        )
                        if (loginResult.isFailure) {
                            return@withContext Result.failure(Exception("Session expired and re-login failed"))
                        }
                    } else {
                        return@withContext Result.failure(Exception("No valid credentials"))
                    }
                }
                
                val response = getApiService().getTickets(
                    sessionToken = preferenceManager.sessionToken,
                    userId = preferenceManager.userId
                )
                
                if (response.isSuccessful && response.body() != null) {
                    val tickets = response.body()!!
                    // Save to database
                    val ticketEntities = tickets.map { TicketEntity.fromTicket(it) }
                    ticketDao.insertTickets(ticketEntities)
                    Result.success(tickets)
                } else {
                    if (response.code() == 401) {
                        preferenceManager.clearSessionInfo()
                    }
                    Result.failure(Exception("Failed to fetch tickets: ${response.code()} ${response.message()}"))
                }
            } catch (e: IOException) {
                Result.failure(Exception("Network error: ${e.message}"))
            } catch (e: HttpException) {
                if (e.code() == 401) {
                    preferenceManager.clearSessionInfo()
                }
                Result.failure(Exception("HTTP error: ${e.message}"))
            } catch (e: Exception) {
                Result.failure(Exception("Unknown error: ${e.message}"))
            }
        }
    }
    
    suspend fun getLocalTickets(): List<TicketEntity> {
        return withContext(Dispatchers.IO) {
            ticketDao.getAllTickets()
        }
    }
    
    suspend fun detectTicketChanges(newTickets: List<Ticket>): TicketChanges {
        return withContext(Dispatchers.IO) {
            val oldTickets = ticketDao.getAllTickets()
            val oldTicketsMap = oldTickets.associateBy { it.id }
            
            val newTicketsMap = newTickets.associateBy { it.id }
            
            val newTicketIds = newTicketsMap.keys - oldTicketsMap.keys
            val newTicketsList = newTicketIds.mapNotNull { newTicketsMap[it] }
            
            val updatedTickets = mutableListOf<Ticket>()
            val closedTickets = mutableListOf<Ticket>()
            
            // Check for updates and closed tickets
            for (ticket in newTickets) {
                val oldTicket = oldTicketsMap[ticket.id] ?: continue
                
                // Skip if no changes
                if (oldTicket.dateModified == ticket.dateModified) {
                    continue
                }
                
                // Check if ticket was closed
                if (!Ticket.isTicketClosed(oldTicket.status) && Ticket.isTicketClosed(ticket.status)) {
                    closedTickets.add(ticket)
                }
                // Otherwise it's just an update
                else if (oldTicket.status != ticket.status || 
                         oldTicket.dateModified != ticket.dateModified) {
                    updatedTickets.add(ticket)
                }
            }
            
            TicketChanges(
                newTickets = newTicketsList,
                updatedTickets = updatedTickets,
                closedTickets = closedTickets
            )
        }
    }
    
    data class TicketChanges(
        val newTickets: List<Ticket>,
        val updatedTickets: List<Ticket>,
        val closedTickets: List<Ticket>
    )
}

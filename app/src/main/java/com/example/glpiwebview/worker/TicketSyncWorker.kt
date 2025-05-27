package com.example.glpiwebview.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.glpiwebview.notification.NotificationHelper
import com.example.glpiwebview.repository.TicketRepository

class TicketSyncWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {
    
    private val repository = TicketRepository(appContext)
    private val notificationHelper = NotificationHelper(appContext)
    
    companion object {
        private const val TAG = "TicketSyncWorker"
    }
    
    override suspend fun doWork(): Result {
        Log.d(TAG, "Starting ticket sync")
        
        try {
            // Vérifier si nous avons des identifiants valides
            if (!repository.hasValidCredentials()) {
                Log.e(TAG, "Pas d'identifiants API valides")
                return Result.failure()
            }
            
            Log.d(TAG, "Récupération des tickets depuis l'API GLPI")
            // Fetch tickets from API
            val ticketsResult = repository.refreshTickets()
            
            if (ticketsResult.isSuccess) {
                val tickets = ticketsResult.getOrNull() ?: emptyList()
                Log.d(TAG, "Tickets récupérés: ${tickets.size}")
                
                // Detect changes
                val changes = repository.detectTicketChanges(tickets)
                Log.d(TAG, "Changements détectés - Nouveaux: ${changes.newTickets.size}, " +
                        "Mis à jour: ${changes.updatedTickets.size}, Fermés: ${changes.closedTickets.size}")
                
                // Show notifications for new tickets
                changes.newTickets.forEach { ticket ->
                    Log.d(TAG, "Affichage notification nouveau ticket: ${ticket.id} - ${ticket.title}")
                    notificationHelper.showNewTicketNotification(ticket)
                }
                
                // Show notifications for updated tickets
                changes.updatedTickets.forEach { ticket ->
                    Log.d(TAG, "Affichage notification ticket mis à jour: ${ticket.id} - ${ticket.title}")
                    notificationHelper.showUpdatedTicketNotification(ticket)
                }
                
                // Show notifications for closed tickets
                changes.closedTickets.forEach { ticket ->
                    Log.d(TAG, "Affichage notification ticket fermé: ${ticket.id} - ${ticket.title}")
                    notificationHelper.showClosedTicketNotification(ticket)
                }
                
                Log.d(TAG, "Sync completed successfully")
                return Result.success()
            } else {
                val error = ticketsResult.exceptionOrNull()?.message ?: "Unknown error"
                Log.e(TAG, "Failed to sync tickets: $error")
                return Result.retry()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Exception during ticket sync: ${e.message}", e)
            return Result.retry()
        }
    }
}

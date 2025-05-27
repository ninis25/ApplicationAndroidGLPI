package com.example.glpiwebview.db

import androidx.room.*
import com.example.glpiwebview.api.TicketEntity

@Dao
interface TicketDao {
    @Query("SELECT * FROM tickets ORDER BY dateModified DESC")
    suspend fun getAllTickets(): List<TicketEntity>
    
    @Query("SELECT * FROM tickets WHERE id = :ticketId")
    suspend fun getTicketById(ticketId: Int): TicketEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTickets(tickets: List<TicketEntity>)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTicket(ticket: TicketEntity)
    
    @Query("DELETE FROM tickets")
    suspend fun deleteAllTickets()
}

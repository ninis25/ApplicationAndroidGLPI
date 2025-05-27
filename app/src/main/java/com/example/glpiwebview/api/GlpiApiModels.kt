package com.example.glpiwebview.api

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

// Request models
data class LoginRequest(
    val login: String,
    val password: String
)

// Response models
data class SessionResponse(
    @SerializedName("session_token") val sessionToken: String,
    @SerializedName("id") val userId: Int
)

data class Ticket(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val title: String,
    @SerializedName("date_mod") val dateModified: String,
    @SerializedName("status") val status: Int,
    @SerializedName("urgency") val urgency: Int,
    @SerializedName("requesters_id") val requesterId: Int,
    @SerializedName("date_creation") val dateCreation: String,
    @SerializedName("closedate") val closeDate: String? = null,
    @SerializedName("solvedate") val solveDate: String? = null,
    @SerializedName("content") val content: String? = null,
    @SerializedName("requester") val requesterName: String? = null
) {
    companion object {
        // Status constants from GLPI
        const val STATUS_NEW = 1
        const val STATUS_ASSIGNED = 2
        const val STATUS_PLANNED = 3
        const val STATUS_PENDING = 4
        const val STATUS_SOLVED = 5
        const val STATUS_CLOSED = 6
        
        // Urgency constants from GLPI
        const val URGENCY_VERY_LOW = 1
        const val URGENCY_LOW = 2
        const val URGENCY_MEDIUM = 3
        const val URGENCY_HIGH = 4
        const val URGENCY_VERY_HIGH = 5
        
        fun isTicketClosed(status: Int): Boolean {
            return status == STATUS_SOLVED || status == STATUS_CLOSED
        }
    }
}

// Database entity for local storage
@Entity(tableName = "tickets")
data class TicketEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val dateModified: String,
    val status: Int,
    val urgency: Int,
    val requesterId: Int,
    val requesterName: String?,
    val dateCreation: String,
    val closeDate: String?,
    val solveDate: String?,
    val content: String?
) {
    companion object {
        fun fromTicket(ticket: Ticket): TicketEntity {
            return TicketEntity(
                id = ticket.id,
                title = ticket.title,
                dateModified = ticket.dateModified,
                status = ticket.status,
                urgency = ticket.urgency,
                requesterId = ticket.requesterId,
                requesterName = ticket.requesterName,
                dateCreation = ticket.dateCreation,
                closeDate = ticket.closeDate,
                solveDate = ticket.solveDate,
                content = ticket.content
            )
        }
    }
}

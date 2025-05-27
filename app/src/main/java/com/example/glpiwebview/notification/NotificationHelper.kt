package com.example.glpiwebview.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.glpiwebview.MainActivity
import com.example.glpiwebview.R
import com.example.glpiwebview.api.Ticket
import com.google.android.material.snackbar.Snackbar

class NotificationHelper(private val context: Context) {
    
    companion object {
        private const val CHANNEL_ID = "glpi_notifications"
        private const val NOTIFICATION_ID_NEW = 1000
        private const val NOTIFICATION_ID_UPDATED = 2000
        private const val NOTIFICATION_ID_CLOSED = 3000
    }
    
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    
    init {
        createNotificationChannel()
    }
    
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "ITSM Notifications"
            val descriptionText = "Notifications pour les tickets ITSM"
            val importance = NotificationManager.IMPORTANCE_MAX // Importance maximale pour les pop-ups
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
                enableLights(true)
                lightColor = android.graphics.Color.RED // Couleur de la LED
                enableVibration(true)
                vibrationPattern = longArrayOf(0, 500, 200, 500) // Vibration forte
                lockscreenVisibility = Notification.VISIBILITY_PUBLIC
                setShowBadge(true) // Afficher un badge sur l'icône de l'app
                setBypassDnd(true) // Contourner le mode "Ne pas déranger"
            }
            notificationManager.createNotificationChannel(channel)
            Log.d("NotificationHelper", "Canal de notification créé avec importance maximale pour pop-ups")
        }
    }
    
    fun showNewTicketNotification(ticket: Ticket) {
        val title = "📥 Nouveau ticket : ${ticket.title}"
        val content = "Urgence: ${getUrgencyText(ticket.urgency)} | Demandeur: ${ticket.requesterName ?: "Non spécifié"}"
        
        // Toujours afficher une notification système
        showSystemNotification(NOTIFICATION_ID_NEW + ticket.id, title, content)
        
        // Si l'app est en premier plan, afficher aussi un toast
        if (MainActivity.isInForeground) {
            showToast(title, content)
        }
    }
    
    fun showUpdatedTicketNotification(ticket: Ticket) {
        val title = "✏️ Ticket mis à jour : ${ticket.title}"
        val content = "Statut: ${getStatusText(ticket.status)} | Dernière modification: ${formatDate(ticket.dateModified)}"
        
        // Toujours afficher une notification système
        showSystemNotification(NOTIFICATION_ID_UPDATED + ticket.id, title, content)
        
        // Si l'app est en premier plan, afficher aussi un toast
        if (MainActivity.isInForeground) {
            showToast(title, content)
        }
    }
    
    fun showClosedTicketNotification(ticket: Ticket) {
        val closeDate = ticket.closeDate ?: ticket.solveDate ?: ticket.dateModified
        val title = "✅ Ticket fermé : ${ticket.title}"
        val content = "Date de fermeture: ${formatDate(closeDate)} | Demandeur: ${ticket.requesterName ?: "Non spécifié"}"
        
        // Toujours afficher une notification système
        showSystemNotification(NOTIFICATION_ID_CLOSED + ticket.id, title, content)
        
        // Si l'app est en premier plan, afficher aussi un toast
        if (MainActivity.isInForeground) {
            showToast(title, content)
        }
    }
    
    private fun showToast(title: String, content: String) {
        val message = "$title\n$content"
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
    
    private fun showSystemNotification(id: Int, title: String, content: String) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        
        // Créer une notification de type heads-up (pop-up)
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(content)
            .setStyle(NotificationCompat.BigTextStyle().bigText(content))
            .setPriority(NotificationCompat.PRIORITY_MAX) // Priorité maximale pour heads-up
            .setCategory(NotificationCompat.CATEGORY_MESSAGE) // Catégorie message
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL) // Son, vibration et lumière par défaut
            .setVibrate(longArrayOf(0, 500, 200, 500)) // Vibration plus forte
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC) // Visible sur l'écran de verrouillage
            .setFullScreenIntent(pendingIntent, true) // Affichage en plein écran sur écran verrouillé
        
        // Forcer l'affichage de la notification
        notificationManager.notify(id, builder.build())
        
        // Log pour débogage
        Log.d("NotificationHelper", "Notification pop-up envoyée - ID: $id, Titre: $title")
    }
    
    fun showSnackbar(activity: MainActivity, message: String) {
        val rootView = activity.findViewById<android.view.View>(android.R.id.content)
        Snackbar.make(rootView, message, Snackbar.LENGTH_LONG).show()
    }
    
    private fun getUrgencyText(urgency: Int): String {
        return when (urgency) {
            Ticket.URGENCY_VERY_LOW -> "Très basse"
            Ticket.URGENCY_LOW -> "Basse"
            Ticket.URGENCY_MEDIUM -> "Moyenne"
            Ticket.URGENCY_HIGH -> "Haute"
            Ticket.URGENCY_VERY_HIGH -> "Très haute"
            else -> "Non définie"
        }
    }
    
    private fun getStatusText(status: Int): String {
        return when (status) {
            Ticket.STATUS_NEW -> "Nouveau"
            Ticket.STATUS_ASSIGNED -> "Assigné"
            Ticket.STATUS_PLANNED -> "Planifié"
            Ticket.STATUS_PENDING -> "En attente"
            Ticket.STATUS_SOLVED -> "Résolu"
            Ticket.STATUS_CLOSED -> "Clos"
            else -> "Inconnu"
        }
    }
    
    private fun formatDate(dateString: String): String {
        // Simple formatting for now, could be improved
        return dateString.replace("T", " ").substringBefore(".")
    }
}

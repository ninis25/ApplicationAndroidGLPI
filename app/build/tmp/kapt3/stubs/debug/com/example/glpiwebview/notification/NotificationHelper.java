package com.example.glpiwebview.notification;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002J\u0010\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000eH\u0002J\u000e\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0013J\u0016\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\nJ \u0010\u0019\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\nH\u0002J\u0018\u0010\u001d\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\nH\u0002J\u000e\u0010\u001e\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/example/glpiwebview/notification/NotificationHelper;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "notificationManager", "Landroid/app/NotificationManager;", "createNotificationChannel", "", "formatDate", "", "dateString", "getStatusText", "status", "", "getUrgencyText", "urgency", "showClosedTicketNotification", "ticket", "Lcom/example/glpiwebview/api/Ticket;", "showNewTicketNotification", "showSnackbar", "activity", "Lcom/example/glpiwebview/MainActivity;", "message", "showSystemNotification", "id", "title", "content", "showToast", "showUpdatedTicketNotification", "Companion", "app_debug"})
public final class NotificationHelper {
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.glpiwebview.notification.NotificationHelper.Companion Companion = null;
    private static final java.lang.String CHANNEL_ID = "glpi_notifications";
    private static final int NOTIFICATION_ID_NEW = 1000;
    private static final int NOTIFICATION_ID_UPDATED = 2000;
    private static final int NOTIFICATION_ID_CLOSED = 3000;
    private final android.app.NotificationManager notificationManager = null;
    
    public NotificationHelper(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    private final void createNotificationChannel() {
    }
    
    public final void showNewTicketNotification(@org.jetbrains.annotations.NotNull()
    com.example.glpiwebview.api.Ticket ticket) {
    }
    
    public final void showUpdatedTicketNotification(@org.jetbrains.annotations.NotNull()
    com.example.glpiwebview.api.Ticket ticket) {
    }
    
    public final void showClosedTicketNotification(@org.jetbrains.annotations.NotNull()
    com.example.glpiwebview.api.Ticket ticket) {
    }
    
    private final void showToast(java.lang.String title, java.lang.String content) {
    }
    
    private final void showSystemNotification(int id, java.lang.String title, java.lang.String content) {
    }
    
    public final void showSnackbar(@org.jetbrains.annotations.NotNull()
    com.example.glpiwebview.MainActivity activity, @org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    private final java.lang.String getUrgencyText(int urgency) {
        return null;
    }
    
    private final java.lang.String getStatusText(int status) {
        return null;
    }
    
    private final java.lang.String formatDate(java.lang.String dateString) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/example/glpiwebview/notification/NotificationHelper$Companion;", "", "()V", "CHANNEL_ID", "", "NOTIFICATION_ID_CLOSED", "", "NOTIFICATION_ID_NEW", "NOTIFICATION_ID_UPDATED", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
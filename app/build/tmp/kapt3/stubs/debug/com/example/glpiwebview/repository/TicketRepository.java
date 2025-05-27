package com.example.glpiwebview.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001 B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001f\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J\b\u0010\u0011\u001a\u00020\u0006H\u0002J\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u000eH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014J\u0006\u0010\u0015\u001a\u00020\u0016J2\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aH\u0086@\u00f8\u0001\u0001\u00f8\u0001\u0002\u00f8\u0001\u0000\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001c\u0010\u001dJ(\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u0018H\u0086@\u00f8\u0001\u0001\u00f8\u0001\u0002\u00f8\u0001\u0000\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001f\u0010\u0014R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006!"}, d2 = {"Lcom/example/glpiwebview/repository/TicketRepository;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "apiService", "Lcom/example/glpiwebview/api/GlpiApiService;", "preferenceManager", "Lcom/example/glpiwebview/PreferenceManager;", "ticketDao", "Lcom/example/glpiwebview/db/TicketDao;", "detectTicketChanges", "Lcom/example/glpiwebview/repository/TicketRepository$TicketChanges;", "newTickets", "", "Lcom/example/glpiwebview/api/Ticket;", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getApiService", "getLocalTickets", "Lcom/example/glpiwebview/api/TicketEntity;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hasValidCredentials", "", "login", "Lkotlin/Result;", "username", "", "password", "login-0E7RQCE", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refreshTickets", "refreshTickets-IoAF18A", "TicketChanges", "app_debug"})
public final class TicketRepository {
    private final android.content.Context context = null;
    private final com.example.glpiwebview.PreferenceManager preferenceManager = null;
    private final com.example.glpiwebview.db.TicketDao ticketDao = null;
    private com.example.glpiwebview.api.GlpiApiService apiService;
    
    public TicketRepository(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    public final boolean hasValidCredentials() {
        return false;
    }
    
    private final com.example.glpiwebview.api.GlpiApiService getApiService() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getLocalTickets(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.glpiwebview.api.TicketEntity>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object detectTicketChanges(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.glpiwebview.api.Ticket> newTickets, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.glpiwebview.repository.TicketRepository.TicketChanges> continuation) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0007J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J9\u0010\u000f\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t\u00a8\u0006\u0017"}, d2 = {"Lcom/example/glpiwebview/repository/TicketRepository$TicketChanges;", "", "newTickets", "", "Lcom/example/glpiwebview/api/Ticket;", "updatedTickets", "closedTickets", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getClosedTickets", "()Ljava/util/List;", "getNewTickets", "getUpdatedTickets", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"})
    public static final class TicketChanges {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.example.glpiwebview.api.Ticket> newTickets = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.example.glpiwebview.api.Ticket> updatedTickets = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.example.glpiwebview.api.Ticket> closedTickets = null;
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.glpiwebview.repository.TicketRepository.TicketChanges copy(@org.jetbrains.annotations.NotNull()
        java.util.List<com.example.glpiwebview.api.Ticket> newTickets, @org.jetbrains.annotations.NotNull()
        java.util.List<com.example.glpiwebview.api.Ticket> updatedTickets, @org.jetbrains.annotations.NotNull()
        java.util.List<com.example.glpiwebview.api.Ticket> closedTickets) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public TicketChanges(@org.jetbrains.annotations.NotNull()
        java.util.List<com.example.glpiwebview.api.Ticket> newTickets, @org.jetbrains.annotations.NotNull()
        java.util.List<com.example.glpiwebview.api.Ticket> updatedTickets, @org.jetbrains.annotations.NotNull()
        java.util.List<com.example.glpiwebview.api.Ticket> closedTickets) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.example.glpiwebview.api.Ticket> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.example.glpiwebview.api.Ticket> getNewTickets() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.example.glpiwebview.api.Ticket> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.example.glpiwebview.api.Ticket> getUpdatedTickets() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.example.glpiwebview.api.Ticket> component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.example.glpiwebview.api.Ticket> getClosedTickets() {
            return null;
        }
    }
}
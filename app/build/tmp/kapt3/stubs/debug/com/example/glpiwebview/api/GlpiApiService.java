package com.example.glpiwebview.api;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016Jc\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\u00072\b\b\u0003\u0010\t\u001a\u00020\n2\b\b\u0003\u0010\u000b\u001a\u00020\u00072\b\b\u0001\u0010\f\u001a\u00020\n2\b\b\u0003\u0010\r\u001a\u00020\u000e2\b\b\u0003\u0010\u000f\u001a\u00020\u000eH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J+\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\u0013\u001a\u00020\u0014H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0017"}, d2 = {"Lcom/example/glpiwebview/api/GlpiApiService;", "", "getTickets", "Lretrofit2/Response;", "", "Lcom/example/glpiwebview/api/Ticket;", "appToken", "", "sessionToken", "field", "", "searchType", "userId", "expandDropdowns", "", "getHateoas", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;IZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "login", "Lcom/example/glpiwebview/api/SessionResponse;", "loginRequest", "Lcom/example/glpiwebview/api/LoginRequest;", "(Ljava/lang/String;Lcom/example/glpiwebview/api/LoginRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"})
public abstract interface GlpiApiService {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.glpiwebview.api.GlpiApiService.Companion Companion = null;
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.POST(value = "initSession")
    public abstract java.lang.Object login(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "App-Token")
    java.lang.String appToken, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.example.glpiwebview.api.LoginRequest loginRequest, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.glpiwebview.api.SessionResponse>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "Ticket")
    public abstract java.lang.Object getTickets(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "App-Token")
    java.lang.String appToken, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Session-Token")
    java.lang.String sessionToken, @retrofit2.http.Query(value = "criteria[0][field]")
    int field, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "criteria[0][searchtype]")
    java.lang.String searchType, @retrofit2.http.Query(value = "criteria[0][value]")
    int userId, @retrofit2.http.Query(value = "expand_dropdowns")
    boolean expandDropdowns, @retrofit2.http.Query(value = "get_hateoas")
    boolean getHateoas, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.example.glpiwebview.api.Ticket>>> continuation);
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 3)
    public final class DefaultImpls {
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/glpiwebview/api/GlpiApiService$Companion;", "", "()V", "create", "Lcom/example/glpiwebview/api/GlpiApiService;", "baseUrl", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.glpiwebview.api.GlpiApiService create(@org.jetbrains.annotations.NotNull()
        java.lang.String baseUrl) {
            return null;
        }
    }
}
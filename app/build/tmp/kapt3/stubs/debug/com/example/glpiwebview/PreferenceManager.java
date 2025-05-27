package com.example.glpiwebview;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\f\u0018\u0000 42\u00020\u0001:\u00014B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010(\u001a\u00020)J\u0006\u0010*\u001a\u00020\fJ\u0006\u0010+\u001a\u00020\fJ\u0006\u0010,\u001a\u00020\u0006J\u0006\u0010-\u001a\u00020\u0006J\u0006\u0010.\u001a\u00020\u0006J\u0016\u0010/\u001a\u00020)2\u0006\u00100\u001a\u00020\f2\u0006\u00101\u001a\u00020\fJ\u0016\u00102\u001a\u00020)2\u0006\u0010\u001d\u001a\u00020\f2\u0006\u0010#\u001a\u00020\"J\u0016\u00103\u001a\u00020)2\u0006\u0010\u0017\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\fR$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR$\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R$\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0015\u0010\t\"\u0004\b\u0016\u0010\u000bR$\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0018\u0010\u000f\"\u0004\b\u0019\u0010\u0011R$\u0010\u001a\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u001b\u0010\u000f\"\u0004\b\u001c\u0010\u0011R$\u0010\u001d\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u001e\u0010\u000f\"\u0004\b\u001f\u0010\u0011R\u000e\u0010 \u001a\u00020!X\u0082\u0004\u00a2\u0006\u0002\n\u0000R$\u0010#\u001a\u00020\"2\u0006\u0010\u0005\u001a\u00020\"8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b$\u0010%\"\u0004\b&\u0010\'\u00a8\u00065"}, d2 = {"Lcom/example/glpiwebview/PreferenceManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "value", "", "areCredentialsSaved", "getAreCredentialsSaved", "()Z", "setAreCredentialsSaved", "(Z)V", "", "glpiPassword", "getGlpiPassword", "()Ljava/lang/String;", "setGlpiPassword", "(Ljava/lang/String;)V", "glpiUsername", "getGlpiUsername", "setGlpiUsername", "isFirstLaunch", "setFirstLaunch", "protocol", "getProtocol", "setProtocol", "serverAddress", "getServerAddress", "setServerAddress", "sessionToken", "getSessionToken", "setSessionToken", "sharedPreferences", "Landroid/content/SharedPreferences;", "", "userId", "getUserId", "()I", "setUserId", "(I)V", "clearSessionInfo", "", "getApiBaseUrl", "getFullUrl", "hasValidApiCredentials", "hasValidSession", "hasValidSettings", "saveApiCredentials", "username", "password", "saveSessionInfo", "saveSettings", "Companion", "app_debug"})
public final class PreferenceManager {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.glpiwebview.PreferenceManager.Companion Companion = null;
    private static final java.lang.String PREF_NAME = "GLPIWebViewPrefs";
    private static final java.lang.String KEY_FIRST_LAUNCH = "first_launch";
    private static final java.lang.String KEY_PROTOCOL = "protocol";
    private static final java.lang.String KEY_SERVER_ADDRESS = "server_address";
    private static final java.lang.String KEY_GLPI_USERNAME = "glpi_username";
    private static final java.lang.String KEY_GLPI_PASSWORD = "glpi_password";
    private static final java.lang.String KEY_SESSION_TOKEN = "session_token";
    private static final java.lang.String KEY_USER_ID = "user_id";
    private static final java.lang.String KEY_CREDENTIALS_SAVED = "credentials_saved";
    private static final java.lang.String DEFAULT_PROTOCOL = "http";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String APP_TOKEN = "Mt9NwDCQrQiwPQaxXbJOQ4bdPZIgK7S0tZlZRHtG";
    private final android.content.SharedPreferences sharedPreferences = null;
    
    public PreferenceManager(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    public final boolean isFirstLaunch() {
        return false;
    }
    
    public final void setFirstLaunch(boolean value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getProtocol() {
        return null;
    }
    
    public final void setProtocol(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getServerAddress() {
        return null;
    }
    
    public final void setServerAddress(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getGlpiUsername() {
        return null;
    }
    
    public final void setGlpiUsername(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getGlpiPassword() {
        return null;
    }
    
    public final void setGlpiPassword(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSessionToken() {
        return null;
    }
    
    public final void setSessionToken(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final int getUserId() {
        return 0;
    }
    
    public final void setUserId(int value) {
    }
    
    public final boolean getAreCredentialsSaved() {
        return false;
    }
    
    public final void setAreCredentialsSaved(boolean value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFullUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getApiBaseUrl() {
        return null;
    }
    
    public final boolean hasValidSettings() {
        return false;
    }
    
    public final boolean hasValidApiCredentials() {
        return false;
    }
    
    public final boolean hasValidSession() {
        return false;
    }
    
    public final void saveSettings(@org.jetbrains.annotations.NotNull()
    java.lang.String protocol, @org.jetbrains.annotations.NotNull()
    java.lang.String serverAddress) {
    }
    
    public final void saveApiCredentials(@org.jetbrains.annotations.NotNull()
    java.lang.String username, @org.jetbrains.annotations.NotNull()
    java.lang.String password) {
    }
    
    public final void saveSessionInfo(@org.jetbrains.annotations.NotNull()
    java.lang.String sessionToken, int userId) {
    }
    
    public final void clearSessionInfo() {
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/example/glpiwebview/PreferenceManager$Companion;", "", "()V", "APP_TOKEN", "", "DEFAULT_PROTOCOL", "KEY_CREDENTIALS_SAVED", "KEY_FIRST_LAUNCH", "KEY_GLPI_PASSWORD", "KEY_GLPI_USERNAME", "KEY_PROTOCOL", "KEY_SERVER_ADDRESS", "KEY_SESSION_TOKEN", "KEY_USER_ID", "PREF_NAME", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
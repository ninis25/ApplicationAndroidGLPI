package com.example.glpiwebview

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {
    companion object {
        private const val PREF_NAME = "GLPIWebViewPrefs"
        private const val KEY_FIRST_LAUNCH = "first_launch"
        private const val KEY_PROTOCOL = "protocol"
        private const val KEY_SERVER_ADDRESS = "server_address"
        private const val KEY_GLPI_USERNAME = "glpi_username"
        private const val KEY_GLPI_PASSWORD = "glpi_password"
        private const val KEY_SESSION_TOKEN = "session_token"
        private const val KEY_USER_ID = "user_id"
        private const val KEY_CREDENTIALS_SAVED = "credentials_saved"
        
        // Default values
        private const val DEFAULT_PROTOCOL = "http"
        const val APP_TOKEN = "Mt9NwDCQrQiwPQaxXbJOQ4bdPZIgK7S0tZlZRHtG"
    }
    
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    
    var isFirstLaunch: Boolean
        get() = sharedPreferences.getBoolean(KEY_FIRST_LAUNCH, true)
        set(value) = sharedPreferences.edit().putBoolean(KEY_FIRST_LAUNCH, value).apply()
    
    var protocol: String
        get() = sharedPreferences.getString(KEY_PROTOCOL, DEFAULT_PROTOCOL) ?: DEFAULT_PROTOCOL
        set(value) = sharedPreferences.edit().putString(KEY_PROTOCOL, value).apply()
    
    var serverAddress: String
        get() = sharedPreferences.getString(KEY_SERVER_ADDRESS, "") ?: ""
        set(value) = sharedPreferences.edit().putString(KEY_SERVER_ADDRESS, value).apply()
        
    var glpiUsername: String
        get() = sharedPreferences.getString(KEY_GLPI_USERNAME, "") ?: ""
        set(value) = sharedPreferences.edit().putString(KEY_GLPI_USERNAME, value).apply()
        
    var glpiPassword: String
        get() = sharedPreferences.getString(KEY_GLPI_PASSWORD, "") ?: ""
        set(value) = sharedPreferences.edit().putString(KEY_GLPI_PASSWORD, value).apply()
        
    var sessionToken: String
        get() = sharedPreferences.getString(KEY_SESSION_TOKEN, "") ?: ""
        set(value) = sharedPreferences.edit().putString(KEY_SESSION_TOKEN, value).apply()
        
    var userId: Int
        get() = sharedPreferences.getInt(KEY_USER_ID, -1)
        set(value) = sharedPreferences.edit().putInt(KEY_USER_ID, value).apply()
        
    var areCredentialsSaved: Boolean
        get() = sharedPreferences.getBoolean(KEY_CREDENTIALS_SAVED, false)
        set(value) = sharedPreferences.edit().putBoolean(KEY_CREDENTIALS_SAVED, value).apply()
    
    fun getFullUrl(): String {
        return "$protocol://$serverAddress"
    }
    
    fun getApiBaseUrl(): String {
        return "${getFullUrl()}/apirest.php"
    }
    
    fun hasValidSettings(): Boolean {
        return serverAddress.isNotBlank()
    }
    
    fun hasValidApiCredentials(): Boolean {
        return areCredentialsSaved && glpiUsername.isNotBlank() && glpiPassword.isNotBlank()
    }
    
    fun hasValidSession(): Boolean {
        return sessionToken.isNotBlank() && userId > 0
    }
    
    fun saveSettings(protocol: String, serverAddress: String) {
        this.protocol = protocol
        this.serverAddress = serverAddress
        this.isFirstLaunch = false
    }
    
    fun saveApiCredentials(username: String, password: String) {
        this.glpiUsername = username
        this.glpiPassword = password
        this.areCredentialsSaved = true
    }
    
    fun saveSessionInfo(sessionToken: String, userId: Int) {
        this.sessionToken = sessionToken
        this.userId = userId
    }
    
    fun clearSessionInfo() {
        this.sessionToken = ""
        this.userId = -1
    }
}

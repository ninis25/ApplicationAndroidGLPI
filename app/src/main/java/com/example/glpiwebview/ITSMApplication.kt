package com.example.glpiwebview

import android.app.Application
import android.util.Log

/**
 * Classe Application personnalisée pour initialiser les configurations globales
 */
class ITSMApplication : Application() {
    
    companion object {
        private const val TAG = "ITSMApplication"
    }
    
    override fun onCreate() {
        super.onCreate()
        
        // Initialiser le gestionnaire de confiance SSL pour accepter tous les certificats
        Log.d(TAG, "Initialisation du gestionnaire de confiance SSL")
        SSLTrustManager.trustAllCertificates()
    }
}

package com.example.glpiwebview

import android.util.Log
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSession
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

/**
 * Gestionnaire de confiance SSL qui accepte tous les certificats
 * ATTENTION: À utiliser uniquement en environnement de développement ou dans des cas spécifiques
 * où la sécurité n'est pas une préoccupation majeure.
 */
object SSLTrustManager {
    
    private val TAG = "SSLTrustManager"
    
    /**
     * Configure l'application pour accepter tous les certificats SSL
     */
    fun trustAllCertificates() {
        try {
            // Créer un gestionnaire de confiance qui ne valide pas les chaînes de certificats
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
                
                override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
                    // Ne rien faire, accepter tous les certificats clients
                }
                
                override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
                    // Ne rien faire, accepter tous les certificats serveurs
                }
            })
            
            // Installer le gestionnaire de confiance
            val sslContext = SSLContext.getInstance("TLS")
            sslContext.init(null, trustAllCerts, SecureRandom())
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.socketFactory)
            
            // Installer un vérificateur d'hôte qui accepte tous les hôtes
            HttpsURLConnection.setDefaultHostnameVerifier(object : HostnameVerifier {
                override fun verify(hostname: String, session: SSLSession): Boolean {
                    return true
                }
            })
            
            Log.d(TAG, "Tous les certificats SSL sont maintenant acceptés")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur lors de la configuration du gestionnaire de confiance SSL: ${e.message}")
        }
    }
}

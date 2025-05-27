package com.example.glpiwebview

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ErrorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error)
        
        // Récupérer le type d'erreur
        val errorType = intent.getStringExtra("ERROR_TYPE") ?: "TIMEOUT"
        
        // Configurer les textes en fonction du type d'erreur
        val titleTextView = findViewById<TextView>(R.id.errorTitleTextView)
        val messageTextView = findViewById<TextView>(R.id.errorMessageTextView)
        
        when (errorType) {
            "SSL" -> {
                titleTextView.text = "Erreur de sécurité HTTPS"
                messageTextView.text = "Impossible d'établir une connexion sécurisée avec le serveur ITSM. Le certificat du serveur n'est pas valide ou n'est pas reconnu."
            }
            "CONNECTION" -> {
                titleTextView.text = "Connexion refusée"
                messageTextView.text = "Impossible de se connecter au serveur ITSM. Vérifiez que l'adresse du serveur est correcte et que le serveur est accessible."
            }
            else -> {
                titleTextView.text = "Délai d'attente dépassé"
                messageTextView.text = "Le serveur ITSM met trop de temps à répondre. Vérifiez votre connexion internet ou les paramètres du serveur."
            }
        }
        
        // Configurer les boutons
        findViewById<Button>(R.id.retryButton).setOnClickListener {
            // Retourner à MainActivity avec un flag pour relancer le chargement
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("RETRY_LOADING", true)
            startActivity(intent)
            finish()
        }
        
        findViewById<Button>(R.id.settingsButton).setOnClickListener {
            // Aller à l'écran des paramètres
            startActivity(Intent(this, SettingsActivity::class.java))
            finish()
        }
    }
    
    // Empêcher le retour arrière
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        // Ne rien faire pour empêcher le retour arrière
    }
}

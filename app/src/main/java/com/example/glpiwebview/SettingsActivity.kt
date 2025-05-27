package com.example.glpiwebview

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {
    
    private lateinit var preferenceManager: PreferenceManager
    private lateinit var protocolRadioGroup: RadioGroup
    private lateinit var httpRadioButton: RadioButton
    private lateinit var httpsRadioButton: RadioButton
    private lateinit var serverAddressEditText: EditText
    private lateinit var saveButton: Button
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        
        preferenceManager = PreferenceManager(this)
        
        // Initialiser les vues
        protocolRadioGroup = findViewById(R.id.protocolRadioGroup)
        httpRadioButton = findViewById(R.id.httpRadioButton)
        httpsRadioButton = findViewById(R.id.httpsRadioButton)
        serverAddressEditText = findViewById(R.id.serverAddressEditText)
        saveButton = findViewById(R.id.saveButton)
        
        // Charger les paramètres existants s'ils existent
        if (!preferenceManager.isFirstLaunch) {
            if (preferenceManager.protocol == "https") {
                httpsRadioButton.isChecked = true
            } else {
                httpRadioButton.isChecked = true
            }
            serverAddressEditText.setText(preferenceManager.serverAddress)
        }
        
        // Configurer le bouton de sauvegarde
        saveButton.setOnClickListener {
            saveSettings()
        }
    }
    
    private fun saveSettings() {
        val protocol = if (httpsRadioButton.isChecked) "https" else "http"
        val serverAddress = serverAddressEditText.text.toString().trim()
        
        // Vérifier que l'adresse du serveur n'est pas vide
        if (serverAddress.isEmpty()) {
            Toast.makeText(this, R.string.error_empty_server, Toast.LENGTH_SHORT).show()
            return
        }
        
        // Enregistrer les paramètres
        preferenceManager.saveSettings(protocol, serverAddress)
        
        // Afficher un message de confirmation
        Toast.makeText(this, R.string.settings_saved, Toast.LENGTH_SHORT).show()
        
        // Rediriger vers l'activité principale
        startActivity(Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        })
        finish()
    }
}

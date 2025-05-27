package com.example.glpiwebview

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class SplashActivity : AppCompatActivity() {
    
    private val splashTimeOut: Long = 2500 // 2,5 secondes pour correspondre à l'iOS
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Rendre la barre de statut transparente
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
        
        setContentView(R.layout.activity_splash)
        
        // Récupérer les vues
        val logoImageView: ImageView = findViewById(R.id.logoImageView)
        val progressBar: ProgressBar = findViewById(R.id.loadingProgressBar)
        
        // Animation de fondu pour le logo
        val fadeIn = AlphaAnimation(0.0f, 1.0f)
        fadeIn.duration = 500
        fadeIn.fillAfter = true
        
        // Démarrer l'animation du logo
        logoImageView.startAnimation(fadeIn)
        
        // Délai avant de passer à l'écran suivant
        Handler(Looper.getMainLooper()).postDelayed({
            val preferenceManager = PreferenceManager(this)
            
            // Animation de fondu pour la sortie
            val fadeOut = AlphaAnimation(1.0f, 0.0f)
            fadeOut.duration = 300
            fadeOut.fillAfter = true
            fadeOut.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {}
                
                override fun onAnimationEnd(animation: Animation?) {
                    // Vérifier si c'est le premier lancement ou si les paramètres sont valides
                    if (preferenceManager.isFirstLaunch || !preferenceManager.hasValidSettings()) {
                        // Rediriger vers l'activité des paramètres
                        startActivity(Intent(this@SplashActivity, SettingsActivity::class.java))
                    } else {
                        // Rediriger vers l'activité principale
                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    }
                    
                    // Fermer cette activité
                    finish()
                }
                
                override fun onAnimationRepeat(animation: Animation?) {}
            })
            
            // Démarrer l'animation de sortie
            logoImageView.startAnimation(fadeOut)
            progressBar.startAnimation(fadeOut)
            
        }, splashTimeOut)
    }
}

package com.example.glpiwebview

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.webkit.*
import android.widget.Toast
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.lifecycleScope
import androidx.work.*
import com.example.glpiwebview.notification.NotificationHelper
import com.example.glpiwebview.repository.TicketRepository
import com.example.glpiwebview.worker.TicketSyncWorker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    
    private lateinit var webView: WebView
    private lateinit var settingsButton: FloatingActionButton
    private lateinit var preferenceManager: PreferenceManager
    private var isLoginPage = false
    
    private var filePathCallback: ValueCallback<Array<Uri>>? = null
    private var cameraPhotoPath: String? = null
    private var timeoutDialog: Dialog? = null
    private var loadingDialog: Dialog? = null
    private var pageLoadTimeout: Handler? = null
    private val TIMEOUT_DELAY = TimeUnit.SECONDS.toMillis(5) // 5 secondes
    
    private lateinit var repository: TicketRepository
    private lateinit var notificationHelper: NotificationHelper
    private var loginDialog: AlertDialog? = null
    
    companion object {
        private const val TAG = "MainActivity"
        private const val CAMERA_PERMISSION_REQUEST = 100
        private const val STORAGE_PERMISSION_REQUEST = 101
        private const val SYNC_INTERVAL_MINUTES = 1L // 1 minute
        private const val SYNC_INTERVAL_SECONDS = 15L // 15 secondes pour les tests
        
        // Track if the app is in foreground to decide between toast and system notifications
        var isInForeground = false
            private set
    }
    
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Configuration du plein écran améliorée
        window.decorView.systemUiVisibility = (
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        )
        window.statusBarColor = ContextCompat.getColor(this, R.color.glpi_blue)
        
        setContentView(R.layout.activity_main)
        
        preferenceManager = PreferenceManager(this)
        repository = TicketRepository(this)
        notificationHelper = NotificationHelper(this)
        
        // Vérifier si les paramètres sont valides
        if (!preferenceManager.hasValidSettings()) {
            startActivity(Intent(this, SettingsActivity::class.java))
            finish()
            return
        }
        
        // Vérifier si nous devons relancer le chargement après une erreur
        val shouldRetry = intent.getBooleanExtra("RETRY_LOADING", false)
        
        // Initialiser les vues
        webView = findViewById(R.id.webView)
        settingsButton = findViewById(R.id.settingsButton)
        
        // Configurer la WebView
        setupWebView()
        
        // Si nous revenons de l'écran d'erreur avec le flag de relance
        if (shouldRetry) {
            // Relancer le chargement après un court délai
            Handler(Looper.getMainLooper()).postDelayed({
                webView.reload()
            }, 500)
        }
        
        // Configurer le bouton des paramètres
        settingsButton.apply {
            setImageResource(R.drawable.ic_settings)
            backgroundTintList = android.content.res.ColorStateList.valueOf(resources.getColor(R.color.colorAccent, theme))
            imageTintList = android.content.res.ColorStateList.valueOf(resources.getColor(R.color.white, theme))
            visibility = View.GONE // Caché par défaut, visible uniquement sur la page de connexion
            setOnClickListener {
                startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
            }
        }
        
        // Démarrer le service de synchronisation si nous avons des identifiants API valides
        if (preferenceManager.hasValidApiCredentials()) {
            setupPeriodicSync()
        }
    }
    
    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        // Configurer les paramètres de la WebView
        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            loadWithOverviewMode = true
            useWideViewPort = true
            builtInZoomControls = true
            displayZoomControls = false
            cacheMode = WebSettings.LOAD_DEFAULT
            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            allowFileAccess = true
            allowContentAccess = true
            allowFileAccessFromFileURLs = true
            allowUniversalAccessFromFileURLs = true
            mediaPlaybackRequiresUserGesture = false
            
            // Désactiver l'affichage des erreurs standard
            setBlockNetworkLoads(false)
        }
        
        // Configurer le WebViewClient pour gérer les redirections et les erreurs dans la WebView
        webView.webViewClient = object : WebViewClient() {
            // Méthode pour les versions récentes d'Android
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                // Garder tous les liens dans la WebView
                return false
            }
            
            // Méthode pour les versions antérieures d'Android
            @Suppress("DEPRECATION")
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                return false
            }
            
            // Intercepter toutes les erreurs de ressources (versions récentes d'Android)
            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                // Ne pas appeler super pour empêcher l'affichage de l'erreur standard
                if (request?.isForMainFrame == true) {
                    Log.e(TAG, "Erreur WebView: ${error?.description}")
                    
                    // Annuler tout timeout précédent
                    pageLoadTimeout?.removeCallbacksAndMessages(null)
                    
                    // Fermer le dialogue de chargement s'il est affiché
                    dismissLoadingDialog()
                    
                    // Arrêter le chargement de la page
                    view?.stopLoading()
                    
                    // Lancer l'activité d'erreur
                    startActivity(Intent(this@MainActivity, ErrorActivity::class.java).apply {
                        putExtra("ERROR_TYPE", "CONNECTION")
                    })
                }
            }
            
            // Méthode pour les versions antérieures d'Android
            @Suppress("DEPRECATION")
            override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {
                // Ancienne méthode pour les versions antérieures d'Android
                // Annuler tout timeout précédent
                pageLoadTimeout?.removeCallbacksAndMessages(null)
                
                // Fermer le dialogue de chargement s'il est affiché
                dismissLoadingDialog()
                
                // Arrêter le chargement de la page
                view.stopLoading()
                
                // Lancer l'activité d'erreur
                startActivity(Intent(this@MainActivity, ErrorActivity::class.java).apply {
                    putExtra("ERROR_TYPE", "CONNECTION")
                })
            }
            
            override fun onPageStarted(view: WebView?, url: String?, favicon: android.graphics.Bitmap?) {
                super.onPageStarted(view, url, favicon)
                
                // Nous gardons le dialogue de chargement déjà affiché et le timeout déjà configuré
                // lors de l'appel à loadUrl
            }
            
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                
                // Fermer le dialogue de chargement si présent
                dismissLoadingDialog()
                
                // Annuler le timeout
                pageLoadTimeout?.removeCallbacksAndMessages(null)
                pageLoadTimeout = null
                
                // Détecter si nous sommes sur la page de connexion
                val isLoginPage = url.contains("/index.php") || url.endsWith("/")
                
                // Afficher ou masquer le bouton des paramètres selon la page
                settingsButton.visibility = if (isLoginPage) View.VISIBLE else View.GONE
                
                // Détecter si l'utilisateur est connecté (présence du menu utilisateur)
                view.evaluateJavascript("""
                    (function() {
                        // Vérifier si l'utilisateur est connecté (présence du menu utilisateur)
                        var userMenu = document.querySelector('.navbar-nav.navbar-right .dropdown-toggle');
                        return userMenu !== null;
                    })();
                """.trimIndent()) { result ->
                    val isLoggedIn = result.equals("true", ignoreCase = true)
                    
                    // Si l'utilisateur est connecté et que nous n'avons pas encore les identifiants API
                    if (isLoggedIn && !preferenceManager.hasValidApiCredentials()) {
                        // Afficher le dialogue de connexion à l'API après un court délai
                        Handler(Looper.getMainLooper()).postDelayed({
                            showApiLoginDialog()
                        }, 1000)
                    }
                }
                
                // Injecter du JavaScript pour améliorer l'interface utilisateur
                view.evaluateJavascript("""
                    // Remplacer les mentions "GLPI" par "ITSM"
                    document.querySelectorAll('*').forEach(function(node) {
                        if (node.nodeType === Node.TEXT_NODE) {
                            node.textContent = node.textContent.replace(/GLPI/g, 'ITSM');
                        }
                    });
                    
                    // Modifier le titre de la page
                    if (document.title.includes('GLPI')) {
                        document.title = document.title.replace('GLPI', 'ITSM');
                    }
                    
                    // Modifier les éléments spécifiques
                    var elements = document.querySelectorAll('.navbar-brand, .copyright a, footer a');
                    elements.forEach(function(el) {
                        if (el.textContent.includes('GLPI')) {
                            el.textContent = el.textContent.replace('GLPI', 'ITSM');
                        }
                    });
                    
                    // Masquer certains éléments pour une interface plus épurée
                    var style = document.createElement('style');
                    style.textContent = `
                        .copyright, .navbar-header .navbar-brand span {
                            display: none !important;
                        }
                    `;
                    document.head.appendChild(style);
                """.trimIndent(), null)
            }
            
            override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                Log.e(TAG, "Erreur SSL ignorée: ${error?.primaryError}")
                
                // Accepter tous les certificats SSL, même non valides
                handler?.proceed()
            }
            
            // Méthode déjà remplacée par la nouvelle implémentation ci-dessus
        }
        
        // Configurer le WebChromeClient pour améliorer l'expérience utilisateur et gérer les fichiers
        webView.webChromeClient = object : WebChromeClient() {
            // Gérer les demandes de fichiers (pour l'upload de fichiers dans la WebView)
            override fun onShowFileChooser(
                webView: WebView,
                filePathCallback: ValueCallback<Array<Uri>>,
                fileChooserParams: FileChooserParams
            ): Boolean {
                // Annuler toute demande précédente
                this@MainActivity.filePathCallback?.onReceiveValue(null)
                this@MainActivity.filePathCallback = filePathCallback

                // Vérifier les permissions
                if (checkAndRequestPermissions()) {
                    showFileChooserOptions()
                }
                return true
            }
            
            // Gérer les demandes de permissions pour la WebView
            override fun onPermissionRequest(request: PermissionRequest) {
                runOnUiThread {
                    request.grant(request.resources)
                }
            }
        }
        
        // Charger l'URL ITSM
        val itsmUrl = preferenceManager.getFullUrl()
        
        // Afficher le dialogue de chargement avant de charger l'URL
        showLoadingDialog()
        
        // Démarrer le timeout avant même de charger l'URL
        pageLoadTimeout = Handler(Looper.getMainLooper())
        pageLoadTimeout?.postDelayed({
            // Après exactement 5 secondes, afficher l'écran d'erreur
            // Peu importe si la page a chargé ou non, nous forçons l'affichage de l'erreur
            dismissLoadingDialog()
            
            // Arrêter le chargement de la page
            webView.stopLoading()
            
            // Lancer l'activité d'erreur
            startActivity(Intent(this@MainActivity, ErrorActivity::class.java))
            
            // Ne pas terminer cette activité pour pouvoir y revenir après
        }, TIMEOUT_DELAY)
        
        try {
            webView.loadUrl(itsmUrl)
        } catch (e: Exception) {
            Log.e(TAG, "Erreur lors du chargement de l'URL: ${e.message}")
            // Ne rien faire ici, le timer gèrera l'affichage du dialogue d'erreur après 5 secondes
        }
    }
    
    // Gérer le bouton retour pour naviguer dans l'historique de la WebView
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
            // Vérifier à nouveau si nous sommes sur la page de connexion après navigation
            Handler(Looper.getMainLooper()).postDelayed({
                webView.evaluateJavascript(
                    "(function() { return document.querySelector('form[name=\"formlogin\"]') !== null; })();",
                    { result ->
                        isLoginPage = result.equals("true")
                        runOnUiThread {
                            settingsButton.visibility = if (isLoginPage) View.VISIBLE else View.GONE
                        }
                    }
                )
            }, 500)
        } else {
            super.onBackPressed()
        }
    }
    
    // Afficher le dialogue de chargement
    private fun showLoadingDialog() {
        runOnUiThread {
            if (loadingDialog?.isShowing == true) return@runOnUiThread
            
            // Cacher la WebView pendant le chargement
            webView.visibility = View.INVISIBLE
            
            loadingDialog = Dialog(this, android.R.style.Theme_NoTitleBar_Fullscreen).apply {
                setContentView(R.layout.loading_dialog)
                setCancelable(false)
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                
                // Configurer la fenêtre du dialogue pour qu'elle soit en plein écran
                window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
                
                // Assurons-nous que le dialogue est affiché immédiatement
                try {
                    show()
                    Log.d(TAG, "Dialogue de chargement affiché")
                } catch (e: Exception) {
                    Log.e(TAG, "Erreur lors de l'affichage du dialogue de chargement: ${e.message}")
                }
            }
        }
    }
    
    // Fermer le dialogue de chargement
    private fun dismissLoadingDialog() {
        runOnUiThread {
            loadingDialog?.dismiss()
            loadingDialog = null
            
            // Réafficher la WebView après le chargement
            webView.visibility = View.VISIBLE
        }
    }
    
    // Ces méthodes ne sont plus utilisées car nous utilisons maintenant une activité dédiée pour l'erreur
    // Mais nous les gardons au cas où nous voudrions revenir à l'approche par dialogue
    /*
    private fun showTimeoutErrorDialog() {
        // Remplacé par le lancement de ErrorActivity
    }
    
    private fun dismissTimeoutDialog() {
        // Plus nécessaire
    }
    */
    
    // Vérifier et demander les permissions nécessaires
    private fun checkAndRequestPermissions(): Boolean {
        val permissions = mutableListOf<String>()
        
        // Vérifier la permission de la caméra
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.CAMERA)
        }
        
        // Vérifier les permissions de stockage selon la version d'Android
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.S_V2) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.READ_MEDIA_IMAGES)
            }
        }
        
        // Demander les permissions si nécessaire
        if (permissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, permissions.toTypedArray(), STORAGE_PERMISSION_REQUEST)
            return false
        }
        
        return true
    }
    
    // Gérer le résultat des demandes de permissions
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        
        when (requestCode) {
            CAMERA_PERMISSION_REQUEST, STORAGE_PERMISSION_REQUEST -> {
                if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                    showFileChooserOptions()
                } else {
                    // Annuler la demande de fichier si les permissions sont refusées
                    filePathCallback?.onReceiveValue(null)
                    filePathCallback = null
                }
            }
        }
    }
    
    // Afficher les options pour choisir un fichier ou prendre une photo
    private fun showFileChooserOptions() {
        AlertDialog.Builder(this)
            .setTitle("Sélectionner une source")
            .setItems(arrayOf("Appareil photo", "Galerie")) { _, which ->
                when (which) {
                    0 -> takePictureWithCamera()
                    1 -> chooseFromGallery()
                }
            }
            .setOnCancelListener {
                filePathCallback?.onReceiveValue(null)
                filePathCallback = null
            }
            .show()
    }
    
    // Prendre une photo avec l'appareil photo
    private fun takePictureWithCamera() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            // Créer un fichier temporaire pour la photo
            val photoFile = createImageFile()
            photoFile?.let {
                val photoURI = FileProvider.getUriForFile(
                    this,
                    "com.example.glpiwebview.fileprovider",
                    it
                )
                cameraPhotoPath = "file:${it.absolutePath}"
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                
                try {
                    startActivityForResult(takePictureIntent, 1)
                } catch (e: Exception) {
                    Log.e(TAG, "Error starting camera: ${e.message}")
                    filePathCallback?.onReceiveValue(null)
                    filePathCallback = null
                }
            }
        } else {
            filePathCallback?.onReceiveValue(null)
            filePathCallback = null
        }
    }
    
    // Choisir une image depuis la galerie
    private fun chooseFromGallery() {
        val contentSelectionIntent = Intent(Intent.ACTION_GET_CONTENT)
        contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE)
        contentSelectionIntent.type = "image/*"
        
        try {
            startActivityForResult(contentSelectionIntent, 2)
        } catch (e: Exception) {
            Log.e(TAG, "Error opening gallery: ${e.message}")
            filePathCallback?.onReceiveValue(null)
            filePathCallback = null
        }
    }
    
    // Créer un fichier temporaire pour stocker l'image de la caméra
    private fun createImageFile(): File? {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val imageFileName = "JPEG_${timeStamp}_"
        val storageDir = getExternalFilesDir(null)
        
        return try {
            File.createTempFile(imageFileName, ".jpg", storageDir)
        } catch (e: Exception) {
            Log.e(TAG, "Error creating image file: ${e.message}")
            null
        }
    }
    
    // Gérer le résultat de l'activité (caméra ou galerie)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        
        if (filePathCallback == null) return
        
        // Traiter le résultat selon la source (caméra ou galerie)
        when (requestCode) {
            1 -> { // Caméra
                if (resultCode == Activity.RESULT_OK) {
                    cameraPhotoPath?.let { path ->
                        val result = arrayOf(Uri.parse(path))
                        filePathCallback?.onReceiveValue(result)
                    }
                } else {
                    filePathCallback?.onReceiveValue(null)
                }
                filePathCallback = null
            }
            2 -> { // Galerie
                if (resultCode == Activity.RESULT_OK && data != null) {
                    data.data?.let { uri ->
                        val result = arrayOf(uri)
                        filePathCallback?.onReceiveValue(result)
                    } ?: run {
                        filePathCallback?.onReceiveValue(null)
                    }
                } else {
                    filePathCallback?.onReceiveValue(null)
                }
                filePathCallback = null
            }
        }
    }
    
    override fun onResume() {
        super.onResume()
        isInForeground = true
    }
    
    override fun onPause() {
        super.onPause()
        isInForeground = false
    }
    
    /**
     * Affiche le dialogue de connexion à l'API GLPI
     */
    private fun showApiLoginDialog() {
        // Créer la vue du dialogue
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_api_login, null)
        val usernameInput = dialogView.findViewById<TextInputEditText>(R.id.usernameInput)
        val passwordInput = dialogView.findViewById<TextInputEditText>(R.id.passwordInput)
        val loginButton = dialogView.findViewById<Button>(R.id.loginButton)
        val cancelButton = dialogView.findViewById<Button>(R.id.cancelButton)
        
        // Créer le dialogue
        val builder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
        
        loginDialog = builder.create()
        loginDialog?.show()
        
        // Gérer les clics sur les boutons
        loginButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()
            
            if (username.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            // Désactiver les boutons pendant la connexion
            loginButton.isEnabled = false
            cancelButton.isEnabled = false
            
            // Tenter la connexion à l'API
            lifecycleScope.launch {
                val result = repository.login(username, password)
                
                // Réactiver les boutons
                loginButton.isEnabled = true
                cancelButton.isEnabled = true
                
                if (result.isSuccess) {
                    // Fermer le dialogue et démarrer la synchronisation
                    loginDialog?.dismiss()
                    loginDialog = null
                    
                    // Afficher un message de succès
                    notificationHelper.showSnackbar(this@MainActivity, "Connexion réussie. Les notifications sont activées.")
                    
                    // Démarrer la synchronisation périodique
                    setupPeriodicSync()
                } else {
                    // Afficher l'erreur
                    val errorMessage = result.exceptionOrNull()?.message ?: "Erreur inconnue"
                    Toast.makeText(this@MainActivity, "Échec de connexion: $errorMessage", Toast.LENGTH_LONG).show()
                }
            }
        }
        
        cancelButton.setOnClickListener {
            loginDialog?.dismiss()
            loginDialog = null
        }
    }
    
    /**
     * Configure la synchronisation périodique des tickets
     */
    private fun setupPeriodicSync() {
        // Créer une contrainte pour exécuter le worker périodiquement
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        
        // Créer une requête de travail périodique (15 secondes pour les tests)
        val syncRequest = PeriodicWorkRequestBuilder<TicketSyncWorker>(SYNC_INTERVAL_SECONDS, TimeUnit.SECONDS)
            .setConstraints(constraints)
            .setInitialDelay(5, TimeUnit.SECONDS) // Démarrer après 5 secondes
            .build()
        
        // Enregistrer le travail avec WorkManager
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "ticket_sync_work",
            ExistingPeriodicWorkPolicy.REPLACE,
            syncRequest
        )
        
        // Lancer également une synchronisation immédiate
        val immediateSync = OneTimeWorkRequestBuilder<TicketSyncWorker>()
            .setConstraints(constraints)
            .build()
        
        WorkManager.getInstance(this).enqueue(immediateSync)
        
        Log.d(TAG, "Synchronisation périodique configurée toutes les $SYNC_INTERVAL_SECONDS secondes")
        Toast.makeText(this, "Surveillance des tickets activée", Toast.LENGTH_SHORT).show()
    }
}

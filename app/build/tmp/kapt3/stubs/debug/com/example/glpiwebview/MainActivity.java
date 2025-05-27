package com.example.glpiwebview;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0015\n\u0002\b\n\u0018\u0000 ;2\u00020\u0001:\u0001;B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u001e\u001a\u00020\fH\u0002J\b\u0010\u001f\u001a\u00020 H\u0002J\n\u0010!\u001a\u0004\u0018\u00010\"H\u0002J\b\u0010#\u001a\u00020 H\u0002J\"\u0010$\u001a\u00020 2\u0006\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020&2\b\u0010(\u001a\u0004\u0018\u00010)H\u0014J\b\u0010*\u001a\u00020 H\u0017J\u0012\u0010+\u001a\u00020 2\b\u0010,\u001a\u0004\u0018\u00010-H\u0015J\b\u0010.\u001a\u00020 H\u0014J-\u0010/\u001a\u00020 2\u0006\u0010%\u001a\u00020&2\u000e\u00100\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\t2\u0006\u00101\u001a\u000202H\u0016\u00a2\u0006\u0002\u00103J\b\u00104\u001a\u00020 H\u0014J\b\u00105\u001a\u00020 H\u0002J\b\u00106\u001a\u00020 H\u0003J\b\u00107\u001a\u00020 H\u0002J\b\u00108\u001a\u00020 H\u0002J\b\u00109\u001a\u00020 H\u0002J\b\u0010:\u001a\u00020 H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006<"}, d2 = {"Lcom/example/glpiwebview/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TIMEOUT_DELAY", "", "cameraPhotoPath", "", "filePathCallback", "Landroid/webkit/ValueCallback;", "", "Landroid/net/Uri;", "isLoginPage", "", "loadingDialog", "Landroid/app/Dialog;", "loginDialog", "Landroidx/appcompat/app/AlertDialog;", "notificationHelper", "Lcom/example/glpiwebview/notification/NotificationHelper;", "pageLoadTimeout", "Landroid/os/Handler;", "preferenceManager", "Lcom/example/glpiwebview/PreferenceManager;", "repository", "Lcom/example/glpiwebview/repository/TicketRepository;", "settingsButton", "Lcom/google/android/material/floatingactionbutton/FloatingActionButton;", "timeoutDialog", "webView", "Landroid/webkit/WebView;", "checkAndRequestPermissions", "chooseFromGallery", "", "createImageFile", "Ljava/io/File;", "dismissLoadingDialog", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "onRequestPermissionsResult", "permissions", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onResume", "setupPeriodicSync", "setupWebView", "showApiLoginDialog", "showFileChooserOptions", "showLoadingDialog", "takePictureWithCamera", "Companion", "app_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    private android.webkit.WebView webView;
    private com.google.android.material.floatingactionbutton.FloatingActionButton settingsButton;
    private com.example.glpiwebview.PreferenceManager preferenceManager;
    private boolean isLoginPage = false;
    private android.webkit.ValueCallback<android.net.Uri[]> filePathCallback;
    private java.lang.String cameraPhotoPath;
    private android.app.Dialog timeoutDialog;
    private android.app.Dialog loadingDialog;
    private android.os.Handler pageLoadTimeout;
    private final long TIMEOUT_DELAY = 0L;
    private com.example.glpiwebview.repository.TicketRepository repository;
    private com.example.glpiwebview.notification.NotificationHelper notificationHelper;
    private androidx.appcompat.app.AlertDialog loginDialog;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.glpiwebview.MainActivity.Companion Companion = null;
    private static final java.lang.String TAG = "MainActivity";
    private static final int CAMERA_PERMISSION_REQUEST = 100;
    private static final int STORAGE_PERMISSION_REQUEST = 101;
    private static final long SYNC_INTERVAL_MINUTES = 1L;
    private static final long SYNC_INTERVAL_SECONDS = 15L;
    private static boolean isInForeground = false;
    
    public MainActivity() {
        super();
    }
    
    @android.annotation.SuppressLint(value = {"SetJavaScriptEnabled"})
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @android.annotation.SuppressLint(value = {"SetJavaScriptEnabled"})
    private final void setupWebView() {
    }
    
    @java.lang.Override()
    @java.lang.Deprecated()
    public void onBackPressed() {
    }
    
    private final void showLoadingDialog() {
    }
    
    private final void dismissLoadingDialog() {
    }
    
    private final boolean checkAndRequestPermissions() {
        return false;
    }
    
    @java.lang.Override()
    public void onRequestPermissionsResult(int requestCode, @org.jetbrains.annotations.NotNull()
    java.lang.String[] permissions, @org.jetbrains.annotations.NotNull()
    int[] grantResults) {
    }
    
    private final void showFileChooserOptions() {
    }
    
    private final void takePictureWithCamera() {
    }
    
    private final void chooseFromGallery() {
    }
    
    private final java.io.File createImageFile() {
        return null;
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    @java.lang.Override()
    protected void onPause() {
    }
    
    /**
     * Affiche le dialogue de connexion à l'API GLPI
     */
    private final void showApiLoginDialog() {
    }
    
    /**
     * Configure la synchronisation périodique des tickets
     */
    private final void setupPeriodicSync() {
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082T\u00a2\u0006\u0002\n\u0000R$\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f@BX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0011"}, d2 = {"Lcom/example/glpiwebview/MainActivity$Companion;", "", "()V", "CAMERA_PERMISSION_REQUEST", "", "STORAGE_PERMISSION_REQUEST", "SYNC_INTERVAL_MINUTES", "", "SYNC_INTERVAL_SECONDS", "TAG", "", "<set-?>", "", "isInForeground", "()Z", "setInForeground", "(Z)V", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final boolean isInForeground() {
            return false;
        }
        
        private final void setInForeground(boolean p0) {
        }
    }
}
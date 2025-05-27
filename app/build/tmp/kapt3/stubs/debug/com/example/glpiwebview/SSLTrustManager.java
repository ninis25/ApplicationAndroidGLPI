package com.example.glpiwebview;

import java.lang.System;

/**
 * Gestionnaire de confiance SSL qui accepte tous les certificats
 * ATTENTION: À utiliser uniquement en environnement de développement ou dans des cas spécifiques
 * où la sécurité n'est pas une préoccupation majeure.
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/example/glpiwebview/SSLTrustManager;", "", "()V", "TAG", "", "trustAllCertificates", "", "app_debug"})
public final class SSLTrustManager {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.glpiwebview.SSLTrustManager INSTANCE = null;
    private static final java.lang.String TAG = "SSLTrustManager";
    
    private SSLTrustManager() {
        super();
    }
    
    /**
     * Configure l'application pour accepter tous les certificats SSL
     */
    public final void trustAllCertificates() {
    }
}
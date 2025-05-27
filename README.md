# Application ITSM MibTech pour Android

## Description

ITSM MibTech est une application Android qui permet d'afficher une interface ITSM/GLPI dans une WebView native, offrant une expérience utilisateur optimisée et épurée. L'application encapsule l'interface web ITSM dans une application native, en remplaçant les mentions "GLPI" par "ITSM" pour une meilleure cohérence de marque.

## Fonctionnalités

- **Splash screen** au démarrage :
  - Logo `test.png` centré
  - Fond dégradé sombre
  - Animation de chargement (2-3 secondes)

- **Configuration initiale** lors du premier lancement uniquement :
  - Choix du protocole (HTTP/HTTPS)
  - Saisie de l'adresse IP ou du nom de domaine du serveur ITSM

- **Interface principale** :
  - WebView en plein écran sans URL visible
  - Sans ActionBar pour une expérience immersive
  - Remplacement des mentions "GLPI" par "ITSM"
  - Bouton flottant rose toujours accessible pour les réglages

- **Stockage local** des paramètres de connexion pour les lancements ultérieurs

## Prérequis techniques

- Android Studio (version récente recommandée)
- SDK Android (API 21 minimum, Android 5.0 Lollipop)
- JDK : OpenJDK version 11.0.27 (Temurin)
- Gradle : Version 7.5

## Installation pour développeurs

1. Clonez ou téléchargez ce dépôt
2. Ouvrez le projet dans Android Studio
3. Synchronisez le projet avec les fichiers Gradle
4. Assurez-vous que le fichier `gradle-wrapper.jar` est présent dans le dossier `gradle/wrapper/`
5. Exécutez l'application sur un émulateur ou un appareil physique

## Génération de l'APK

### Méthode 1 : Via Android Studio (Debug APK)

1. Ouvrez le projet dans Android Studio
2. Sélectionnez `Build` dans le menu principal
3. Cliquez sur `Build Bundle(s) / APK(s)` 
4. Sélectionnez `Build APK(s)`
5. Une notification apparaîtra une fois la génération terminée
6. Cliquez sur `locate` dans la notification pour trouver le fichier APK
7. Le fichier APK sera généré dans le dossier `app/build/outputs/apk/debug/`

### Méthode 2 : Via Android Studio (Release APK signé)

1. Ouvrez le projet dans Android Studio
2. Sélectionnez `Build` dans le menu principal
3. Cliquez sur `Generate Signed Bundle / APK...`
4. Sélectionnez `APK` et cliquez sur `Next`
5. Configurez le keystore :
   - Utilisez le fichier `mibtech.keystore` existant dans le projet
   - Entrez les informations d'identification du keystore (mot de passe, alias, etc.)
   - Cliquez sur `Next`
6. Sélectionnez `release` comme variante de build
7. Cochez les options `V1 (Jar Signature)` et `V2 (Full APK Signature)`
8. Cliquez sur `Finish`
9. Le fichier APK signé sera généré dans `app/release/`

### Méthode 3 : Via ligne de commande

1. Ouvrez un terminal à la racine du projet
2. Exécutez la commande suivante pour générer un APK debug :
   ```
   ./gradlew assembleDebug
   ```
3. Ou exécutez la commande suivante pour générer un APK release :
   ```
   ./gradlew assembleRelease
   ```
4. Les fichiers APK seront générés dans :
   - Debug : `app/build/outputs/apk/debug/app-debug.apk`
   - Release : `app/build/outputs/apk/release/app-release.apk`

## Installation de l'APK sur un appareil Android

### Méthode 1 : Installation directe

1. Transférez le fichier APK sur votre appareil Android (via USB, email, cloud, etc.)
2. Sur votre appareil, naviguez jusqu'à l'emplacement du fichier APK
3. Appuyez sur le fichier APK pour lancer l'installation
4. Si demandé, autorisez l'installation d'applications provenant de sources inconnues
5. Suivez les instructions à l'écran pour terminer l'installation

### Méthode 2 : Installation via ADB (pour développeurs)

1. Connectez votre appareil Android à votre ordinateur via USB
2. Activez le débogage USB sur votre appareil
3. Ouvrez un terminal et exécutez :
   ```
   adb install chemin/vers/votre/app.apk
   ```

## Structure du projet

- `app/src/main/java/com/example/itsmwebview/` : Code source Kotlin
  - `SplashActivity.kt` : Écran de démarrage avec animation
  - `SettingsActivity.kt` : Écran de configuration des paramètres serveur
  - `MainActivity.kt` : Écran principal avec WebView et bouton flottant
  - `PreferenceManager.kt` : Gestion des préférences et stockage local

- `app/src/main/res/` : Ressources
  - `layout/` : Fichiers de mise en page XML
  - `drawable/` : Images et ressources graphiques (incluant `test.png` et `logo-G-100-black.png`)
  - `values/` : Chaînes de caractères, styles et couleurs

## Personnalisation

- **Logo de l'application** : Remplacez le fichier `logo-G-100-black.png` dans le dossier `app/src/main/res/drawable/`
- **Logo du splash screen** : Remplacez le fichier `test.png` dans le dossier `app/src/main/res/drawable/`
- **Couleurs de l'application** : Modifiez le fichier `app/src/main/res/values/colors.xml`
- **Textes et libellés** : Modifiez le fichier `app/src/main/res/values/strings.xml`

## Résolution des problèmes courants

### Problème de synchronisation Gradle

Si vous rencontrez des problèmes de synchronisation Gradle, vérifiez que :
- Le fichier `gradle-wrapper.jar` est présent dans le dossier `gradle/wrapper/`
- Les versions de Gradle et du JDK correspondent aux prérequis (Gradle 7.5, JDK 11)
- Le fichier `local.properties` pointe vers votre SDK Android

### Erreur de compilation

En cas d'erreur de compilation, vérifiez :
- Les dépendances dans le fichier `app/build.gradle`
- La compatibilité des versions des bibliothèques utilisées
- Les permissions dans le fichier `AndroidManifest.xml`

## Licence

© 2025 MibTech. Tous droits réservés.

## Contact

Pour toute question ou assistance technique, veuillez contacter l'équipe de développement MibTech.
f
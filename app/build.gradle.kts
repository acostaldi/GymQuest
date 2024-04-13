plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("plugin.serialization") version "1.9.23"
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.GymQuest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.GymQuest"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.database)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.play.services.auth)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // Fuel HTTP client for making network requests
    implementation("com.github.kittinunf.fuel:fuel:2.3.1")

    //packages
    //implementation("com.github.kittinunf.fuel:<package>:2.3.1")

    // Kotlinx Serialization for JSON serialization/deserialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    // AndroidX Core library
    implementation("androidx.core:core-ktx:1.12.0")

    // AndroidX AppCompat library
    implementation("androidx.appcompat:appcompat:<latest-version>")

    // Google Material Design library
    implementation("com.google.android.material:material:<latest-version>")

    //
    // Google Play Store Integration for Authentication //
    //

//    // Import the BoM for the Firebase platform
//    implementation(platform("com.google.firebase:firebase-bom:32.8.1"))
//
//    // Add the dependency for the Firebase Authentication library
//    // When using the BoM, you don't specify versions in Firebase library dependencies
//    implementation("com.google.firebase:firebase-auth")
//
//    // Also add the dependency for the Google Play services library and specify its version
//    implementation("com.google.android.gms:play-services-auth:21.0.0")
}
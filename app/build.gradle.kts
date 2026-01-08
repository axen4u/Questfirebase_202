

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    // Sekarang plugin ini bakal ditemuin karena udah didaftarin di Root
    alias(libs.plugins.google.services)
}

android {
    namespace = "com.example.questfirebase"
    compileSdk = 35 // Gw naikin ke 35 biar aman, minimal 34

    defaultConfig {
        applicationId = "com.example.questfirebase"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // === FIREBASE SETUP (VIA TOML) ===
    // Import BOM (Bill of Materials) biar versi anak-anaknya sinkron
    implementation(platform(libs.firebase.bom))

    // Firestore (Gak perlu tulis versi lagi, ikut BOM)
    implementation(libs.firebase.firestore)

    // Coroutines support buat Firebase (await()) - PENTING!
    // Kita tembak manual aja krn ini jarang ada di catalog standar, atau bisa lu tambah ke toml
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.3")

    // === ANDROID STANDARD ===
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    // === TESTING ===
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}

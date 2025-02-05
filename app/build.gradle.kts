plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("plugin.serialization")
    kotlin("kapt") // Add this line for Kotlin annotation processing
}


android {
    namespace = "com.example.ktor"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.ktor"
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


    // Hilt dependencies

    // Optional: Hilt ViewModel extension

    // Ktor
    implementation (libs.ktor.client.android)
    implementation (libs.ktor.client.serialization)
    implementation (libs.ktor.client.content.negotiation)
    implementation (libs.ktor.client.logging)
    implementation (libs.ktor.client.okhttp)

    implementation (libs.ktor.serialization.kotlinx.json) // JSON Serialization Support
    implementation (libs.kotlinx.serialization.json) // Kotlinx JSON Serialization
    implementation(libs.ktor.client.cio)

        // app compat
    implementation(libs.androidx.appcompat) // Use the latest version


    //splash screen
    implementation(libs.androidx.core.splashscreen) // Check for latest version

    // Kotlin Coroutines
    implementation (libs.kotlinx.coroutines.android)


    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    implementation (libs.blurry) // Check for the latest version on GitHub

    implementation (libs.cloudy) // For blurred effects

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.play.services.location)
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.common.jvm)
    implementation(libs.androidx.room.ktx)
    //noinspection KaptUsageInsteadOfKsp,UseTomlInstead
    kapt(libs.androidx.room.compiler)  // Annotation processor for Room (KAPT)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
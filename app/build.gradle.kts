plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.quran.tafsir"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.quran.tafsir"
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
}

dependencies {
    implementation ("com.google.android.material:material:1.12.0")
    implementation(libs.play.services.ads)
    implementation ("androidx.recyclerview:recyclerview:1.3.2")
    implementation (libs.cardview)
    implementation (libs.glide)
    implementation(libs.volley)
    annotationProcessor (libs.compiler)
    implementation ("com.google.code.gson:gson:2.10.1")
    implementation (libs.google.material.v121)
    implementation (libs.multidex)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
}
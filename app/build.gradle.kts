plugins {
    id("com.android.application")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.example.resq"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.resq"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "MAPS_API_KEY", "\"${project.properties["MAPS_API_KEY"]}\"")





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

    buildFeatures {
        viewBinding = true
        buildConfig = true

    }
}

// ✅ Dependencies should be OUTSIDE the android block
dependencies {
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.play.services.maps)
    implementation(libs.play.services.location)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
//    implementation("com.google.firebase:firebase-auth:22.1.2")
//    implementation("com.google.android.gms:play-services-auth:20.7.0")
//    implementation("com.google.firebase:firebase-database:latest_version")
//    implementation("androidx.appcompat:appcompat:latest_version")
//    implementation("androidx.constraintlayout:constraintlayout:latest_version")
}
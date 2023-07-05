plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.backbase.golden_sample_app"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.backbase.golden_sample_app"
        minSdk = 23
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false

        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    configurations.all {
        resolutionStrategy.force("org.objenesis:objenesis:2.6")
    }
}

dependencies {

    implementation(libraries.bundles.implementation)
    implementation(platform(libraries.compose.bom))
    implementation(libraries.bundles.compose)

    androidTestImplementation(libraries.bundles.androidTest)
    androidTestImplementation(platform(libraries.compose.bom))

    debugImplementation(libraries.bundles.composeDebug)

    testImplementation(libraries.bundles.test)

    // Backbase libraries
    implementation(backbase.bundles.authentication)
    implementation(backbase.bundles.ui)
}
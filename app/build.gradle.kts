plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.detekt)
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
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

    detekt {
        toolVersion = libs.versions.detekt.get()
        buildUponDefaultConfig = true // preconfigure defaults
        allRules = false // activate all available (even unstable) rules.
        config.setFrom("../config/golden-sample-app-detekt.yml") // point to your custom config defining rules to run, overwriting default behavior
        parallel = true
        autoCorrect = true
        ignoredVariants = listOf("release")
    }

}

dependencies {

    implementation(libs.bundles.implementation)
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)

    androidTestImplementation(libs.bundles.androidTest)
    androidTestImplementation(platform(libs.compose.bom))

    debugImplementation(libs.bundles.composeDebug)

    testImplementation(libs.bundles.test)

    detektPlugins(libs.detekt.formatter)

    // Backbase libraries
    implementation(backbase.bundles.authentication)
    implementation(backbase.bundles.ui)
}
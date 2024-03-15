plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.backbase.android.retail"

    defaultConfig {
        applicationId = "com.backbase.android.retail"
        minSdk = 26
        compileSdk = 34
        targetSdk = 34
        versionCode = 1
        versionName = "dev-SNAPSHOT"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
        }
    }

    kotlin {
        jvmToolchain(17)
    }

    buildFeatures { compose = true }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }

    buildFeatures {
        compose = true
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    implementation(platform(libs.kotlin.bom))
    implementation(libs.bundles.android.core)
    implementation(libs.koin.android)

    implementation("com.backbase.android.design:design-system-compose:dev-SNAPSHOT")
    implementation("androidx.compose.ui:ui-viewbinding")
    implementation(backbase.authentication.journey)
    implementation(backbase.authentication.use.case)
    implementation(backbase.retail.journey.common)
    implementation("com.backbase.android.design:country-core:4.0.1!!")
    implementation("com.backbase.android.design:design-system:dev-SNAPSHOT!!")
    implementation(platform(libs1.compose.bom))
    implementation(libs1.bundles.compose)
    debugImplementation(libs1.compose.uiTooling)
    androidTestImplementation(libs.bundles.test.instrumented)
}

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

    implementation("com.backbase.android.design:design-system-compose:1.0.0-beta04")
    implementation("com.backbase.android.design:design-system:5.7.3")
    implementation("com.backbase.android.design:country-core:4.0.1!!")
    implementation(libs.navigation.compose)

    implementation("androidx.navigation:navigation-compose:2.7.7")

    val composeBom = platform("androidx.compose:compose-bom:2024.02.02")
    implementation(composeBom)

    implementation("androidx.compose.ui:ui-viewbinding")

    // or Material Design 2
    implementation("androidx.compose.material:material")
    // or skip Material Design and build directly on top of foundational components
    implementation("androidx.compose.foundation:foundation")
    // or only import the main APIs for the underlying toolkit systems,
    // such as input and measurement/layout
    implementation("androidx.compose.ui:ui")

    // Android Studio Preview support
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // Optional - Integration with activities
    implementation("androidx.activity:activity-compose:1.8.2")
    // Optional - Integration with ViewModels
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")

    implementation(backbase.authentication.journey)
    implementation(backbase.authentication.use.case)
    implementation(backbase.retail.journey.common)

    // UI Tests
    androidTestImplementation(composeBom)
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    androidTestImplementation(libs.bundles.test.instrumented)
}

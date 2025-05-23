import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("com.android.application")
    kotlin("android")
    id(libs.plugins.kotlin.parcelize.get().pluginId)
    id(libs.plugins.navigation.safe.args.get().pluginId)
    id(backbase.plugins.configured.detekt.get().pluginId)
}

android {
    namespace = "com.backbase.accounts_demo"
    compileSdk = Version.compileSdk

    defaultConfig {
        applicationId = "com.backbase.accounts_demo"
        minSdk = Version.minSdk
        targetSdk = Version.compileSdk
        versionCode = Version.versionCode
        versionName = Version.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        compilerOptions { jvmTarget.set(JvmTarget.JVM_17) }
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(projects.appCommon)
    implementation(projects.accountsJourney)
    implementation(projects.accountsUseCase)

    implementation(platform(backbase.bom))

    implementation(platform(libs.kotlin.bom))
    implementation(libs.bundles.android.core)
    implementation(libs.bundles.navigation)
    implementation(libs.bundles.ui)

    coreLibraryDesugaring(libs.coreLibraryDesugaring)

    testImplementation(libs.bundles.test)

    // Backbase libraries
    implementation(clients.bundles.clients)
    implementation(midTier.bundles.common)
    implementation(foundation.bundles.foundation)
    implementation(backbase.bundles.journeys)
    implementation(backbase.bundles.useCases)
}
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

        // The following argument makes the Android Test Orchestrator run its
        // "pm clear" command after each test invocation. This command ensures
        // that the app's state is completely cleared between tests.
        testInstrumentationRunnerArguments["clearPackageData"] = "true"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            buildConfigField("String", "TEST_ACCOUNT_USERNAME", "\"ADD USER NAME HERE\"")
            buildConfigField("String", "TEST_ACCOUNT_PASSWORD", "\"ADD PASSWORD HERE\"")
        }
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
    testOptions {
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
        animationsDisabled = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/LICENSE.md"
            excludes += "META-INF/LICENSE-notice.md"
        }
    }
}

dependencies {
    implementation(projects.analytics)
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

    androidTestImplementation(projects.testData)
    androidTestImplementation(libs.bundles.test.instrumented)

    androidTestUtil(libs.orchestrator)

    // Backbase libraries
    implementation(clientLibs.bundles.bomOutput)
    implementation(midTierLibs.bundles.bomOutput)
    implementation(foundationLibs.bundles.bomOutput)
//    implementation(clients.bundles.clients)
//    implementation(midTier.bundles.common)
//    implementation(foundation.bundles.foundation)
    implementation(backbase.bundles.journeys)
    implementation(backbase.bundles.useCases)
}
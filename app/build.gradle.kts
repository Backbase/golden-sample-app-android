import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("com.android.application")
    kotlin("android")
    id(libs.plugins.kotlin.parcelize.get().pluginId)
    id(libs.plugins.navigation.safe.args.get().pluginId)
    id(backbase.plugins.configured.detekt.get().pluginId)
    id(libs.plugins.karumi.get().pluginId)
    alias(backbase.plugins.visualiser)
}

visualizer {
    themes {
        register("Theme.Backbase.Premium") {
            jsonFile = layout.projectDirectory.file("themes/premiumTokens.json")
        }
        register("Theme.Backbase.Default") {
            jsonFile = layout.projectDirectory.file("themes/defaultTokens.json")
        }
    }
}

android {
    namespace = "com.backbase.golden_sample_app"
    compileSdk = Version.compileSdk

    defaultConfig {
        applicationId = "com.backbase.golden_sample_app"
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
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField(type = "Boolean", name = "DEBUG_MODE", value = "false")
        }
        debug {
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
            buildConfigField(type = "Boolean", name = "DEBUG_MODE", value = "true")
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/LICENSE.md"
            excludes += "META-INF/LICENSE-notice.md"
        }
    }
}

dependencies {
    implementation(projects.network)
    implementation(projects.accountsJourney)
    implementation(projects.accountsUseCase)
    implementation(projects.contactsJourney)

    implementation(platform(backbase.bom))

    implementation(platform(libs.kotlin.bom))
    implementation(libs.bundles.android.core)
    implementation(libs.bundles.navigation)
    implementation(libs.bundles.ui)

    coreLibraryDesugaring(libs.desugar.jdk.libs)
    androidTestImplementation(libs.bundles.test.instrumented)

    testImplementation(libs.bundles.test)

    // Backbase libraries
    implementation(backbase.bundles.clients)
    implementation(backbase.bundles.common)
    implementation(backbase.bundles.foundation)
    implementation(backbase.bundles.journeys)
    implementation(backbase.bundles.use.cases)
}

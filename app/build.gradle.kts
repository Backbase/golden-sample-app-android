plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id(libs.plugins.kotlin.parcelize.get().pluginId)
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        viewBinding = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.8"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/LICENSE.md"
            excludes += "META-INF/LICENSE-notice.md"
        }
    }

    configurations.all {
        resolutionStrategy {
            // Reverting to an older version of objenesis due to build failure during androidTest using mockK
            // Issue link => https://github.com/mockk/mockk/issues/281
            force("org.objenesis:objenesis:2.6")
            // Excluding to avoid "More than one file was found with OS independent path 'META-INF/AL2.0' & 'META-INF/LGPL2.1'"
            // Issue link => https://github.com/Kotlin/kotlinx.coroutines/issues/2023
            exclude(group = "org.jetbrains.kotlinx", module = "kotlinx-coroutines-debug")
        }
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
    implementation(platform(libs.kotlin.bom))
    implementation(libs.bundles.implementation)
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.bundles.navigation)
    implementation(libs.bundles.ui)

    androidTestImplementation(libs.bundles.androidTest)
    androidTestImplementation(platform(libs.compose.bom))

    debugImplementation(libs.bundles.composeDebug)

    testImplementation(libs.bundles.test)

    detektPlugins(libs.detekt.formatter)

    // Backbase libraries
    implementation(backbase.bundles.authentication)
    implementation(backbase.bundles.workspaces)
    implementation(backbase.bundles.ui)
}
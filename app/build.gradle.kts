plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id(libs.plugins.kotlin.parcelize.get().pluginId)
    id(libs.plugins.navigation.safe.args.get().pluginId)
    alias(libs.plugins.detekt)
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
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
    implementation(project(":accounts-journey"))

    implementation(platform(libs.kotlin.bom))
    implementation(libs.bundles.implementation)
    implementation(libs.bundles.navigation)
    implementation(libs.bundles.ui)

    androidTestImplementation(libs.bundles.androidTest)

    testImplementation(libs.bundles.test)

    detektPlugins(libs.detekt.formatter)

    // Backbase libraries
    implementation(backbase.bundles.authentication)
    implementation(backbase.bundles.access.control.client)
    implementation(backbase.bundles.common)
    implementation(backbase.bundles.feature.filter)
    implementation(backbase.bundles.sdk)
    implementation(backbase.bundles.workspaces)
    implementation(backbase.bundles.ui)
}
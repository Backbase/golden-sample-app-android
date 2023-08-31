@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id(libs.plugins.kotlin.parcelize.get().pluginId)
    id(libs.plugins.navigation.safe.args.get().pluginId)
    alias(libs.plugins.poko)
    alias(libs.plugins.detekt)
}

android {
    namespace = "com.backbase.accounts_journey"
    compileSdk = Version.compileSdk

    defaultConfig {
        minSdk = Version.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    kotlinOptions {
        jvmTarget = "11"
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
    implementation(libs.bundles.navigation)
    implementation(libs.bundles.ui)

    androidTestImplementation(libs.bundles.androidTest)

    testImplementation(libs.bundles.test)

    detektPlugins(libs.detekt.formatter)

    // Backbase libraries
    implementation(backbase.bundles.common)
    implementation(backbase.bundles.ui)
}
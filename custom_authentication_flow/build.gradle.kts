plugins {
    id(backbase.plugins.feature.android.module.get().pluginId)
    id(libs.plugins.kotlin.parcelize.get().pluginId)
    id(backbase.plugins.configured.detekt.get().pluginId)
    id(backbase.plugins.jacoco.codecoverage.get().pluginId)
    id(libs.plugins.karumi.get().pluginId)
    id(libs.plugins.navigation.safe.args.get().pluginId)
}

android {
    namespace = "com.backbase.custom_authentication_flow"
    defaultConfig {
        testApplicationId = "com.backbase.custom_authentication_flow.test"
        testInstrumentationRunner = "com.karumi.shot.ShotTestRunner"
        testInstrumentationRunnerArguments["clearPackageData"] = "true"
        testInstrumentationRunnerArguments["useTestStorageService"] = "true"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {

    // Backbase libraries
    implementation(platform(backbase.bom))
    implementation(midTier.bundles.common)
    implementation(libs.bundles.navigation)
    implementation(backbase.authentication.use.case)

    coreLibraryDesugaring(libs.coreLibraryDesugaring)
}
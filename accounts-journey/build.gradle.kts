plugins {
    id(backbase.plugins.feature.android.module.get().pluginId)
    id(libs.plugins.kotlin.parcelize.get().pluginId)
    id(backbase.plugins.configured.detekt.get().pluginId)
    id(backbase.plugins.jacoco.codecoverage.get().pluginId)
    id(libs.plugins.karumi.get().pluginId)
    id(libs.plugins.navigation.safe.args.get().pluginId)
}

android {
    namespace = "com.backbase.accounts_journey"
    defaultConfig {
        testApplicationId = "com.backbase.accounts_journey.test"
        testInstrumentationRunner = "com.karumi.shot.ShotTestRunner"
        testInstrumentationRunnerArguments["clearPackageData"] = "true"
        testInstrumentationRunnerArguments["useTestStorageService"] = "true"
    }
    buildTypes {
        debug {
            enableAndroidTestCoverage = true
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
    testOptions {
        targetSdk = Version.compileSdk
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
        animationsDisabled = true
    }
}

dependencies {
    implementation(libs.bundles.navigation)
    testImplementation(projects.testData)
    testImplementation(projects.accountsTestData)

    // Backbase libraries
    implementation(platform(backbase.bom))
//    implementation(midTier.bundles.common)
    implementation(midTierLibs.bundles.bomOutput)

    coreLibraryDesugaring(libs.coreLibraryDesugaring)

    testImplementation(libs.archCore)

    androidTestImplementation(projects.accountsTestData)
    androidTestImplementation(projects.testData)
    androidTestImplementation(libs.bundles.test.instrumented)

    androidTestUtil(libs.orchestrator)

    implementation("com.onekosmos.blockid.sdk:blockidsdk:1.20.55.68CBE323")

    implementation("androidx.security:security-crypto:1.1.0")
    // Fingerprint and biometric
    implementation("androidx.biometric:biometric:1.1.0")
    // Network call
    implementation("com.github.amitshekhariitbhu.Fast-Android-Networking:android-networking:1.0.4")
    // LiveID
    implementation("com.google.guava:guava:32.0.1-jre")
}

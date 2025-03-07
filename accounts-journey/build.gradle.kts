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
    testOptions {
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
    }
}

dependencies {
    implementation(libs.bundles.navigation)
    testImplementation(projects.testData)

    androidTestImplementation(libs.navigation.testing)

    // Backbase libraries
    implementation(platform(backbase.bom))
    implementation(backbase.bundles.common)
    implementation(libs.bundles.navigation)

    testImplementation(libs.archCore)
    androidTestImplementation(projects.fakeAccountsUseCase)
    androidTestImplementation(libs.archCore)
    androidTestImplementation(libs.navigation.testing)
    androidTestImplementation(libs.koinTest)
    androidTestImplementation(libs.coroutines)
    androidTestImplementation(libs.coroutinesTest)
    androidTestImplementation(libs.testParameterInjector)
    androidTestUtil(libs.orchestrator)

    androidTestImplementation(projects.fakeAccountsUseCase)
}

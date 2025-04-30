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
    implementation(project(":analytics"))
    implementation(libs.bundles.navigation)
    testImplementation(projects.testData)

    // Backbase libraries
    implementation(platform(backbase.bom))
    implementation(foundation.observability)
    implementation(midTier.bundles.common)
    implementation(libs.bundles.navigation)

    testImplementation(libs.archCore)

    androidTestImplementation(projects.fakeAccountsUseCase)
    androidTestImplementation(libs.bundles.test.instrumented)

    androidTestUtil(libs.orchestrator)
}

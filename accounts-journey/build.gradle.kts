plugins {
    id(backbase.plugins.feature.android.module.get().pluginId)
    id(libs.plugins.kotlin.parcelize.get().pluginId)
    id(libs.plugins.navigation.safe.args.get().pluginId)
    id(backbase.plugins.configured.detekt.get().pluginId)
    id(libs.plugins.karumi.get().pluginId)
}
android {
    namespace = "com.backbase.accounts_journey"
    defaultConfig {
        testApplicationId = "com.backbase.accounts_journey.test"
        testInstrumentationRunner = "com.karumi.shot.ShotTestRunner"
    }
}
dependencies {
    implementation(project(":analytics"))

    // Backbase libraries
    implementation(backbase.observability)
    implementation(backbase.bundles.common)
    implementation(libs.bundles.navigation)

    testImplementation(libs.archCore)
    androidTestImplementation(project("path" to ":fake-accounts-use-case"))
    androidTestImplementation(libs.archCore)
    androidTestImplementation(libs.navigation.testing)
    androidTestImplementation(libs.koinTest)
    androidTestImplementation(libs.coroutines)
    androidTestImplementation(libs.coroutinesTest)
    androidTestImplementation(libs.testParameterInjector)
}

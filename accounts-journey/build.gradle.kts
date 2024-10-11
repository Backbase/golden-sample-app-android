plugins {
    id(backbase.plugins.feature.android.module.get().pluginId)
    id(libs.plugins.kotlin.parcelize.get().pluginId)
    id(backbase.plugins.configured.detekt.get().pluginId)
    id(libs.plugins.navigation.safe.args.get().pluginId)
}

android {
    namespace = "com.backbase.accounts_journey"
}

dependencies {
    implementation(libs.bundles.navigation)

    androidTestImplementation(libs.navigation.testing)

    // Backbase libraries
    implementation(platform(backbase.bom))
    implementation(backbase.bundles.common)

    androidTestImplementation(projects.fakeAccountsUseCase)
}

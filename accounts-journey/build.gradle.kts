plugins {
    id(backbase.plugins.feature.android.module.get().pluginId)
    id(libs.plugins.kotlin.parcelize.get().pluginId)
    id(libs.plugins.navigation.safe.args.get().pluginId)
    id(backbase.plugins.configured.detekt.get().pluginId)
}

android {
    namespace = "com.backbase.accounts_journey"
}

dependencies {
    // Backbase libraries
    implementation(backbase.bundles.common)
    implementation(libs.bundles.navigation)
    androidTestImplementation(project("path" to ":fake-accounts-use-case"))
}
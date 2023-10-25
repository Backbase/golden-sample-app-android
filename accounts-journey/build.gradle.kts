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
    api(project(":accounts-journey:domain"))
    api(project(":accounts-journey:data"))
    api(project(":accounts-journey:presentation"))

    // Backbase libraries
    implementation(backbase.bundles.common)
}
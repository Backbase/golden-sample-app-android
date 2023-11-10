plugins {
    id(backbase.plugins.feature.android.module.get().pluginId)
    id(libs.plugins.kotlin.parcelize.get().pluginId)
    id(libs.plugins.navigation.safe.args.get().pluginId)
}

android {
    namespace = "com.backbase.presentation"
}

dependencies {
    implementation(project(":accounts-journey:domain"))
    implementation(project(":accounts-journey:common"))
    implementation(project(":accounts-journey:common-android"))

    // Backbase libraries
    implementation(backbase.bundles.common)
}
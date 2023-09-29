plugins {
    id(backbase.plugins.base.android.library.module.get().pluginId)
    id(libs.plugins.kotlin.parcelize.get().pluginId)
    id(libs.plugins.navigation.safe.args.get().pluginId)
    id(backbase.plugins.configured.detekt.get().pluginId)
}

android {
    namespace = "com.backbase.accounts_journey"

    viewBinding {
        enable = true
    }
}

dependencies {
    // Backbase libraries
    implementation(backbase.bundles.common)
    implementation(backbase.bundles.ui)
}
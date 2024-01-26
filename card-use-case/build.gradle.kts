plugins {
    id(backbase.plugins.feature.android.module.get().pluginId)
    id(libs.plugins.kotlin.parcelize.get().pluginId)
    id(libs.plugins.navigation.safe.args.get().pluginId)
    id(backbase.plugins.configured.detekt.get().pluginId)
}

android {
    namespace = "com.backbase.card_use_case"
}

dependencies {

    // Backbase libraries
    implementation(backbase.bundles.card.client.common)
}
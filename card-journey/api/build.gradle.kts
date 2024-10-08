plugins {
    id(backbase.plugins.feature.android.module.get().pluginId)
    id(libs.plugins.kotlin.parcelize.get().pluginId)
    id(libs.plugins.navigation.safe.args.get().pluginId)
    id(backbase.plugins.configured.detekt.get().pluginId)
}

android {
    namespace = "com.backbase.cards_journey.api"
}

dependencies {

    // Backbase libraries
    implementation(backbase.bom)
    implementation(backbase.bundles.card.client.common)
}
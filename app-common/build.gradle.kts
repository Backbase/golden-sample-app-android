plugins {
    id(backbase.plugins.base.android.library.module.get().pluginId)
    id(backbase.plugins.configured.detekt.get().pluginId)
}

android {
    namespace = "com.backbase.app_common"
}

dependencies {
    implementation(platform(backbase.bom))

    implementation(platform(libs.kotlin.bom))
    // Backbase libraries
    implementation(backbase.bundles.common)
    implementation(backbase.bundles.foundation)
    implementation(backbase.bundles.journeys)
    implementation(backbase.bundles.use.cases)
}
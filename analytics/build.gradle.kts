plugins {
    id(backbase.plugins.base.android.library.module.get().pluginId)
    id(backbase.plugins.configured.detekt.get().pluginId)
}

android {
    namespace = "com.backbase.analytics"
}

dependencies {
    implementation(platform(backbase.bom))
    implementation(foundationLibs.observability)
    implementation(midTierLibs.openTelemetryConnector)
    implementation(thirdPartyLibs.androidx.constraintLayout)
    implementation(thirdPartyLibs.androidx.swipeRefreshLayout)
    implementation(thirdPartyLibs.koin.android)
    implementation(thirdPartyLibs.koin.core)
    implementation(thirdPartyLibs.material)
}
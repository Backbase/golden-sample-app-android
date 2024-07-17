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
    // Backbase libraries
    implementation(midTierLibs.retail.journey.common)
    implementation(midTierLibs.retail.journey.commonKoin)
    implementation(midTierLibs.retail.journey.test)
    implementation(midTierLibs.business.journeyCommon)
    implementation(midTierLibs.clients.common)

    implementation(libs.bundles.navigation)
    androidTestImplementation(project(":fake-accounts-use-case"))
}

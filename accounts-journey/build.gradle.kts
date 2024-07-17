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
//    implementation(backbase.bundles.common)
    implementation(midTierLibs.retail.journey.common)
    implementation(midTierLibs.retail.journey.commonKoin)
    implementation(midTierLibs.clients.common)
    implementation(midTierLibs.business.journeyCommon)
    implementation(midTierLibs.retail.journey.test)

    implementation(libs.bundles.navigation)
    androidTestImplementation(project("path" to ":fake-accounts-use-case"))
}

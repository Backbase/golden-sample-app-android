plugins {
    id(backbase.plugins.base.android.library.module.get().pluginId)
    id(backbase.plugins.configured.detekt.get().pluginId)
}

android {
    namespace = "com.backbase.app_common"
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
}

dependencies {
    implementation(platform(libs.kotlin.bom))
    implementation(projects.accountsJourney)
    implementation(projects.accountsUseCase)
    implementation(libs.bundles.navigation)

    coreLibraryDesugaring(libs.coreLibraryDesugaring)

    // Backbase libraries
//    implementation(platform(backbase.bom))
//    implementation(clients.bundles.clients)
//    implementation(midTier.bundles.common)
//    implementation(foundation.bundles.foundation)
    implementation(clientLibs.bundles.bomOutput)
    implementation(midTierLibs.bundles.bomOutput)
    implementation(foundationLibs.bundles.bomOutput)
    implementation(backbase.bundles.journeys)
    implementation(backbase.bundles.useCases)
}

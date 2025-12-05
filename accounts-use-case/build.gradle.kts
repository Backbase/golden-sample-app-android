plugins {
    id(backbase.plugins.base.android.library.module.get().pluginId)
    id(backbase.plugins.configured.detekt.get().pluginId)
    id(backbase.plugins.jacoco.codecoverage.get().pluginId)
}

android {
    namespace = "com.backbase.accounts_use_case"

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
}

dependencies {
    implementation(projects.accountsJourney)
    testImplementation(projects.testData)

    coreLibraryDesugaring(libs.coreLibraryDesugaring)

    // Backbase libraries
    implementation(platform(backbase.bom))
//    implementation(clients.arrangements)
    implementation(clientLibs.bundles.bomOutput)
}

plugins {
    id(backbase.plugins.base.android.library.module.get().pluginId)
    id(backbase.plugins.configured.detekt.get().pluginId)
    id(backbase.plugins.jacoco.codecoverage.get().pluginId)
}

android {
    namespace = "com.backbase.accounts_use_case"
}

dependencies {
    implementation(projects.accountsJourney)
    testImplementation(projects.testData)

    // Backbase libraries
    implementation(platform(backbase.bom))
    implementation(clients.arrangements)
    implementation(midTier.clients.common.coroutines)
}

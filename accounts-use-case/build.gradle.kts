plugins {
    id(backbase.plugins.base.android.library.module.get().pluginId)
    id(backbase.plugins.configured.detekt.get().pluginId)
}

android {
    namespace = "com.backbase.accounts_use_case"
}

dependencies {
    implementation(projects.network)
    implementation(projects.accountsJourney)

    implementation(libs.bundles.ktor)

    // Backbase libraries
    implementation(platform(backbase.bom))
    implementation(backbase.gen.arrangements.client)
}

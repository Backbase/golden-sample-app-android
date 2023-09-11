@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id(backbase.plugins.base.android.library.module.get().pluginId)
    kotlin("android")
}

android {
    namespace = "com.backbase.accounts_use_case"
}

dependencies {
    implementation(project(":accounts-journey"))

    // Backbase libraries
    implementation(backbase.bundles.arrangements.client)
    implementation(backbase.clients.common.coroutines)
}
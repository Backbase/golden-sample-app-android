plugins {
    id(backbase.plugins.base.android.library.module.get().pluginId)
}

android {
    namespace = "com.backbase.accounts_use_case"
}

dependencies {
    implementation(project(":accounts-journey:domain"))
    implementation(project(":accounts-journey:common"))

    // Backbase libraries
    implementation(backbase.bundles.arrangements.client)
}

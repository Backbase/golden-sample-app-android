plugins {
    id(backbase.plugins.base.android.library.module.get().pluginId)
}

android {
    namespace = "com.backbase.accounts_journey"
    defaultConfig {
        minSdk = Version.minSdk
        version = "1.0.0"
    }
}

dependencies {
    implementation(project(":accounts-journey:domain"))
    implementation(project(":accounts-journey:data"))
    api(project(":accounts-journey:presentation"))
}

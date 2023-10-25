plugins {
    id(backbase.plugins.base.android.library.module.get().pluginId)
}

android {
    namespace = "com.backbase.accounts_journey"
}

dependencies {
    api(project(":accounts-journey:domain"))
    api(project(":accounts-journey:data"))
    api(project(":accounts-journey:presentation"))
}

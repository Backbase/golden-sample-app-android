plugins {
    id(backbase.plugins.base.android.library.module.get().pluginId)
    id(backbase.plugins.configured.detekt.get().pluginId)
}

android {
    namespace = "com.backbase.fake_accounts_use_case"
}

dependencies {
    implementation(project(":accounts-journey:domain"))
    implementation(project(":accounts-journey:data"))
}
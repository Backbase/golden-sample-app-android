plugins {
    id(backbase.plugins.base.android.library.module.get().pluginId)
    id(backbase.plugins.configured.detekt.get().pluginId)
}

android {
    namespace = "com.backbase.accounts_use_case"
}

dependencies {
    implementation(project(":accounts-journey"))

    // Backbase libraries
    implementation(platform(backbase.bom))
    implementation(backbase.clients.common.coroutines)
    implementation(backbase.gen.arrangements.client)
}

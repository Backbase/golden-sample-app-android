plugins {
    id(backbase.plugins.base.android.library.module.get().pluginId)
    id(backbase.plugins.configured.detekt.get().pluginId)
}

android {
    namespace = "com.backbase.network"
}

dependencies {
    implementation(libs.bundles.ktor)

    // Backbase libraries
    implementation(backbase.clients.common.coroutines)
}
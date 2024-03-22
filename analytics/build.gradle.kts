plugins {
    id(backbase.plugins.base.android.library.module.get().pluginId)
    id(backbase.plugins.configured.detekt.get().pluginId)
}

android {
    namespace = "com.backbase.analytics"
}

dependencies {
    implementation(backbase.analytics)
    implementation(backbase.observability)
}
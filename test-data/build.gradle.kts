plugins {
    id(backbase.plugins.base.android.library.module.get().pluginId)
    id(backbase.plugins.configured.detekt.get().pluginId)
}

android {
    namespace = "com.backbase.android.test_data"
    testOptions {
        unitTests.all {
            it.failOnNoDiscoveredTests = false
        }
    }
}

dependencies {
    implementation(platform(backbase.bom))

    implementation(libs.coroutinesTest)
    implementation(platform(libs.junit.bom))
    implementation(libs.junit.jupiter)
}

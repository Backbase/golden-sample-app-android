plugins {
    id(backbase.plugins.feature.android.module.get().pluginId)
    id(libs.plugins.kotlin.parcelize.get().pluginId)
    id(backbase.plugins.configured.detekt.get().pluginId)
    id(libs.plugins.karumi.get().pluginId)
    id(libs.plugins.navigation.safe.args.get().pluginId)
}

android {
    namespace = "com.backbase.android.journey.contacts"
    defaultConfig {
        testApplicationId = "com.backbase.accounts_journey.test"
        testInstrumentationRunner = "com.karumi.shot.ShotTestRunner"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
}

dependencies {
    implementation(libs.bundles.navigation)


    // Backbase libraries
    implementation(platform(backbase.bom))
    implementation(backbase.bundles.common)
    implementation(libs.coroutines)
    implementation(libs.coroutinesCore)
    implementation(libs.bundles.ktor)
    implementation(backbase.contacts.client.gen2)
    implementation(libs.bundles.navigation)
    implementation(libs.bundles.compose)

    androidTestImplementation(projects.fakeAccountsUseCase)
    androidTestImplementation(libs.navigation.testing)
    androidTestImplementation(libs.archCore)
    androidTestImplementation(libs.navigation.testing)
    androidTestImplementation(libs.koinTest)
    androidTestImplementation(libs.coroutinesTest)
    androidTestImplementation(libs.testParameterInjector)
    androidTestImplementation(projects.fakeAccountsUseCase)

    testImplementation(libs.archCore)
    testImplementation(libs.coroutinesTest)
}
import androidx.navigation.safe.args.generator.ext.capitalize
import org.jetbrains.kotlin.gradle.internal.ensureParentDirsCreated
import java.util.Locale

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.backbase.android.retail"

    defaultConfig {
        applicationId = "com.backbase.android.retail"
        minSdk = 26
        compileSdk = 34
        targetSdk = 34
        versionCode = 1
        versionName = "dev-SNAPSHOT"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
        }
    }

    kotlin {
        jvmToolchain(17)
    }

    buildFeatures { compose = true }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }

    buildFeatures {
        compose = true
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/LICENSE.md"
            excludes += "META-INF/LICENSE-notice.md"
        }
    }
}

androidComponents {
    onVariants { variant ->
        val configCreationTask =
            project.tasks.register<ConfigCreatorTask>("create${variant.name.capitalize(Locale.getDefault())}Config")
        variant.sources.assets?.addGeneratedSourceDirectory(
            configCreationTask,
            ConfigCreatorTask::outputDirectory
        )
    }
}

abstract class ConfigCreatorTask : DefaultTask() {
    @get:OutputDirectory
    abstract val outputDirectory: DirectoryProperty

    @TaskAction
    fun taskAction() {
        val goldenSampleApiToken:String by project
        val file = File(outputDirectory.get().asFile, "backbase/config.json")
        file.ensureParentDirsCreated()
        file.writeText(
            """
            {
              "development": {
                "debugEnable": true
              },
              "backbase": {
                "serverURL": "https://app.dev.sdbxaz.azure.backbaseservices.com",
                "localModelPath": "${'$'}(contextRoot)/model.json",
                "experience": "",
                "version": "6.1.5",
                "identity": {
                  "baseURL": "https://identity.dev.sdbxaz.azure.backbaseservices.com",
                  "realm": "customer",
                  "clientId": "mobile-client",
                  "applicationKey": "retail"
                }
              },
              "security": {
                "allowedDomains": [
                  "*"
                ]
              },
              "custom": {
                "default-http-headers": {
                  "X-SDBXAZ-API-KEY": "$goldenSampleApiToken"
                }
              },
              "bankTimeZone": "Europe/Amsterdam"
            }

        """.trimIndent()
        )
    }
}


dependencies {
    implementation(platform(libs.kotlin.bom))
    implementation(libs.bundles.android.core)
    implementation(libs.koin.android)

    implementation("com.backbase.android.design:design-system-compose:1.0.0-beta04")
    implementation("com.backbase.android.design:design-system:5.7.3")
    implementation("com.backbase.android.design:country-core:4.0.1!!")
    implementation(libs.navigation.compose)

    implementation("androidx.navigation:navigation-compose:2.7.7")

    val composeBom = platform("androidx.compose:compose-bom:2024.02.02")
    implementation(composeBom)

    implementation("androidx.compose.ui:ui-viewbinding")

    // or Material Design 2
    implementation("androidx.compose.material:material")
    // or skip Material Design and build directly on top of foundational components
    implementation("androidx.compose.foundation:foundation")
    // or only import the main APIs for the underlying toolkit systems,
    // such as input and measurement/layout
    implementation("androidx.compose.ui:ui")

    // Android Studio Preview support
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // Optional - Integration with activities
    implementation("androidx.activity:activity-compose:1.8.2")
    // Optional - Integration with ViewModels
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")

    implementation(backbase.authentication.journey)
    implementation(backbase.authentication.use.case)
    implementation(backbase.retail.contacts.journey)
    implementation(backbase.retail.contacts.journey.usecase)
    implementation(backbase.retail.journey.common)
    implementation(backbase.business.journey.common)
    implementation(backbase.workspaces.use.case)
    implementation(backbase.bundles.access.control.client)
//    implementation(backbase.workspaces.journey)
    implementation("com.backbase.android.business.journey:workspaces-journey:dev-SNAPSHOT!!")

    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")

    // UI Tests
    androidTestImplementation(composeBom)
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    androidTestImplementation(libs.bundles.test.instrumented)
}

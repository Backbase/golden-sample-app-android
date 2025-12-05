enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven {
            name = "backbaseRepo"
            url = uri("https://repo.backbase.com/repo")
            credentials(PasswordCredentials::class)
        }
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
        maven {
            name = "backbaseRepo"
            url = uri("https://repo.backbase.com/repo")
            credentials(PasswordCredentials::class)
        }
    }

    versionCatalogs {
        create("backbase") { from(files("gradle/backbase.versions.toml")) }
//        create("clients") { from(files("gradle/clients.versions.toml")) }
//        create("foundation") { from(files("gradle/foundation.versions.toml")) }
//        create("midTier") { from(files("gradle/midTier.versions.toml")) }

        create("thirdPartyLibs") { from("com.backbase.android.platform:catalog-third-parties:2025.09.03") }
        create("foundationLibs") { from("com.backbase.android.platform:catalog-foundation:2025.09.01") }
        create("midTierLibs") { from("com.backbase.android.platform:catalog-mid-tier:2025.09.01") }
        create("clientLibs") { from("com.backbase.android.platform:catalog-clients:2025.09.03") }
    }
}
rootProject.name = "Golden_Sample_App_Android"
include(":app")
include(":app-common")
include(":accounts-journey")
include(":accounts-use-case")
include(":accounts-test-data")
include(":test-data")
include(":analytics")

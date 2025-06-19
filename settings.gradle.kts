enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenLocal()
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
        mavenLocal()
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
        create("clients") { from(files("gradle/clients.versions.toml")) }
        create("foundation") { from(files("gradle/foundation.versions.toml")) }
        create("midTier") { from(files("gradle/midTier.versions.toml")) }
    }
}
rootProject.name = "Golden_Sample_App_Android"
include(":app")
include(":app-common")
include(":accounts-journey")
include(":accounts-use-case")
include(":accounts-test-data")
include(":accounts-demo")
include(":test-data")

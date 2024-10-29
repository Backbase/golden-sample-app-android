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
    }
}
rootProject.name = "Golden_Sample_App_Android"
include(":app")
include(":accounts-journey")
include(":accounts-use-case")
include(":fake-accounts-use-case")
include(":analytics")
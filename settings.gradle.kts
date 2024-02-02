pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
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
rootProject.name = "Golden Sample App Android"
include(":app")
include(":accounts-journey")
include(":accounts-use-case")
include(":fake-accounts-use-case")
include(":card-journey:impl")
include(":card-journey:api")

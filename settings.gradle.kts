pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        maven {
            setUrl("https://artifacts.backbase.com/android")
            val mvnUser: String by settings
            val mvnPass: String by settings
            credentials {
                username = mvnUser
                password = mvnPass
            }
        }
        mavenLocal()
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
        create("libs1") { from("com.backbase.android.gradle:version-catalog:2023.10.75") }
    }
}
rootProject.name = "Golden Sample App Android"
include(":app")
include(":accounts-journey")
include(":accounts-use-case")
include(":fake-accounts-use-case")
include(":analytics")
include(":retail-app")

pluginManagement {
    plugins {
        id("org.jetbrains.kotlin.plugin.parcelize") version "1.9.0"
    }

    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

val backbaseRepositories = listOf(
    "android3",
    "android-business",
    "android-identity",
    "android-retail3",
    "design-android",
)

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        backbaseRepositories.forEach { repoName ->
            maven {
                val mvnUser: String by settings
                val mvnPass: String by settings

                // TODO change to repo
                setUrl("https://artifacts.backbase.com/$repoName")
                credentials {
                    username = mvnUser
                    password = mvnPass
                }
            }
        }
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
        jcenter()
    }

    versionCatalogs {
        create("backbase") { from(files("gradle/backbase.versions.toml")) }
    }
}
rootProject.name = "Golden Sample App Android"
include(":app")

pluginManagement {
    plugins {
        // TODO move this to buildSrc
        id("org.jetbrains.kotlin.plugin.parcelize") version "1.9.0"
        id("com.android.application") version "8.1.0" apply false
        id("com.android.library") version "8.1.0" apply false
        id("org.jetbrains.kotlin.android") version "1.8.22" apply false
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
                val backbaseRepoUsername: String by settings
                val backbaseRepoEncryptedPassword: String by settings

                setUrl("https://artifacts.backbase.com/$repoName")
                credentials {
                    username = "kawing@backbase.com"
                    password = "AKCp8ohUb1Wkfi1BhbCTcUPoYFgH6SYR6GFUmYoR7NMZmXsVCDyzHxqvmmpQ4Nfok9nEyScdP"
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
include(":accounts-journey")
include(":accounts-use-case")

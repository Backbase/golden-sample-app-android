pluginManagement {
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

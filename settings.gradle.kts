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

                setUrl("https://repo.backbase.com/$repoName")
                credentials {
                    username = backbaseRepoUsername
                    password = backbaseRepoEncryptedPassword
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
include(":accounts-journey:domain")
include(":accounts-journey:data")
include(":accounts-journey:presentation")
include(":accounts-journey:common")
include(":accounts-journey:common-android")

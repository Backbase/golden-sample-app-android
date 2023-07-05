pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

val backbaseRepositories = listOf(
    "android3",
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
            google()
            mavenCentral()
            jcenter()
        }
    }

    versionCatalogs {
        create("backbase") { from(files("gradle/backbase.versions.toml")) }
    }
}
rootProject.name = "Golden Sample App Android"
include(":app")
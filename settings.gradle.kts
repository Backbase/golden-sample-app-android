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

    val version = "2024.04."
    versionCatalogs {
        create("backbase") { from(files("gradle/backbase.versions.toml")) }
//        create("thirdPartyLibs") { from("com.backbase.android.platform:catalog-third-parties:$version") }
        create("foundationLibs") { from("com.backbase.android.platform:catalog-foundation:${version}01") }
        create("midTierLibs") { from("com.backbase.android.platform:catalog-mid-tier:${version}02") }
        create("clientLibs") { from("com.backbase.android.platform:catalog-clients:${version}02") }
    }
}
rootProject.name = "Golden Sample App Android"
include(":app")
include(":accounts-journey")
include(":accounts-use-case")
include(":fake-accounts-use-case")

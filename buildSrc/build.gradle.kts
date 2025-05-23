plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    mavenCentral()
    maven { setUrl("https://jitpack.io") }
    maven {
        name = "backbaseRepo"
        url = uri("https://repo.backbase.com/repo")
        credentials(PasswordCredentials::class)
    }
    gradlePluginPortal()
}

dependencies {
    implementation(gradleApi())
    implementation(libs.gradle)
    implementation(libs.gradle.api)
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.karumi.gradle.plugin)

    implementation(libs.detekt.gradle.plugin)
    implementation(libs.gradle.versions.gradle.plugin)
    implementation(libs.androidx.navigation.safe.args.gradle.plugin)
    implementation(libs.poko.gradle.plugin)
}

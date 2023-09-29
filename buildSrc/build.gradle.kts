plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(gradleApi())
    implementation(libs.gradle)
    implementation(libs.gradle.api)
    implementation(libs.kotlin.gradle.plugin)

    implementation(libs.detekt.gradle.plugin)
    implementation(libs.gradle.versions.gradle.plugin)
    implementation(libs.navigation.safe.args.gradle.plugin)
    implementation(libs.poko.gradle.plugin)
}
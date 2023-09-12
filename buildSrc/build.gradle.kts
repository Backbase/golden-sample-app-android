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

    implementation(libs.navigation.safe.args.plugin)
    implementation(libs.gradle.versions.plugin)
    implementation(libs.detekt)
}
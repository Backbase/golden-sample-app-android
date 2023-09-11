// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.gradle.versions.plugin)
}

// Old way with Navigation Safe Args
buildscript {
    dependencies {
        // TODO: try to move this to buildSrc dependencies
        // TODO: unknown why build tools is required here while it is already added in buidlsrc
        classpath("com.android.tools.build:gradle:8.1.1")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.6.0")
    }
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

tasks.withType<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask> {
    resolutionStrategy {
        componentSelection {
            all {
                if (isNonStable(candidate.version) && !isNonStable(currentVersion)) {
                    reject("Release candidate")
                }
            }
        }
    }
}

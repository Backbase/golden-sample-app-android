plugins {
    id("io.gitlab.arturbosch.detekt")
}

internal val Project.libs: VersionCatalog
    get() =
        project.extensions.getByType<VersionCatalogsExtension>().named("libs")

detekt {
    toolVersion = "1.23.1"
    buildUponDefaultConfig = true // preconfigure defaults
    allRules = false // activate all available (even unstable) rules.
    config.setFrom("../config/golden-sample-app-detekt.yml") // point to your custom config defining rules to run, overwriting default behavior
    parallel = true
    autoCorrect = true
    ignoredVariants = listOf("release")
}

dependencies {
    detektPlugins(libs.findLibrary("detekt.formatting").get())
}
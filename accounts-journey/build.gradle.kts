@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id(backbase.plugins.base.android.library.module.get().pluginId)
    id(libs.plugins.kotlin.parcelize.get().pluginId)
    id(libs.plugins.navigation.safe.args.get().pluginId)
    alias(libs.plugins.poko)
//    alias(libs.plugins.detekt)
}

android {
    namespace = "com.backbase.accounts_journey"
//    detekt {
//        toolVersion = libs.versions.detekt.get()
//        buildUponDefaultConfig = true // preconfigure defaults
//        allRules = false // activate all available (even unstable) rules.
//        config.setFrom("../config/golden-sample-app-detekt.yml") // point to your custom config defining rules to run, overwriting default behavior
//        parallel = true
//        autoCorrect = true
//        ignoredVariants = listOf("release")
//    }
}

dependencies {
//    detektPlugins(libs.detekt.formatter)

    // Backbase libraries
    implementation(backbase.bundles.common)
    implementation(backbase.bundles.ui)
}
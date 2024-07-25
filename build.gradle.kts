// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(backbase.plugins.dependency.updates.get().pluginId)
}
subprojects{
    configurations.all {
        resolutionStrategy {
            // Reverting to an older version of objenesis due to build failure during androidTest using mockK
            // Issue link => https://github.com/mockk/mockk/issues/281
            force("org.objenesis:objenesis:2.6")
            // Excluding to avoid "More than one file was found with OS independent path 'META-INF/AL2.0' & 'META-INF/LGPL2.1'"
            // Issue link => https://github.com/Kotlin/kotlinx.coroutines/issues/2023
            exclude(group = "org.jetbrains.kotlinx", module = "kotlinx-coroutines-debug")
        }
    }
}

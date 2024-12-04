// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(backbase.plugins.dependency.updates.get().pluginId)
    alias(backbase.plugins.visualiser)
    alias(libs.plugins.poko) apply false
}

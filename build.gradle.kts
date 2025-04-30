// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(backbase.plugins.jacoco.root.codecoverage.get().pluginId)
    id(backbase.plugins.dependency.updates.get().pluginId)
    alias(backbase.plugins.visualiser)
}

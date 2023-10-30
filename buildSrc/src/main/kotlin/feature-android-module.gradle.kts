plugins {
    id("base-android-library-module")
}

internal val Project.libs: VersionCatalog
    get() =
        project.extensions.getByType<VersionCatalogsExtension>().named("libs")

android {
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.findBundle("ui").get())
}

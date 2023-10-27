plugins {
    kotlin("jvm")
    id("dev.drewhamilton.poko")
    id("configured-detekt")
}

internal val Project.libs: VersionCatalog
    get() =
        project.extensions.getByType<VersionCatalogsExtension>().named("libs")

configurations.all {
    // MockK uses 3.1, but Mockito uses 2.6. Force to use 2.6 to make it compatible.
    resolutionStrategy.force("org.objenesis:objenesis:2.6")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).coerceAtLeast(1)
    minHeapSize = "256m"
    maxHeapSize = "1024m"
    reports {
        html.required.set(false)
        junitXml.required.set(false)
    }
}

dependencies {
    implementation(platform(libs.findLibrary("kotlin-bom").get()))

    testImplementation(platform(libs.findLibrary("junit5-bom").get()))
    testImplementation(libs.findBundle("test").get())
}
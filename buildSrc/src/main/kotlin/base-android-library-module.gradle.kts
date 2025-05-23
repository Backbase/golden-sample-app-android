import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("com.android.library")
    kotlin("android")
    id("dev.drewhamilton.poko")
}

internal val Project.libs: VersionCatalog
    get() =
        project.extensions.getByType<VersionCatalogsExtension>().named("libs")

android {
    compileSdk = Version.compileSdk
    defaultConfig {
        minSdk = Version.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        compilerOptions { jvmTarget.set(JvmTarget.JVM_17) }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_17.toString()
        }
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
    }

    // JUnit 5 will bundle in files with identical paths; exclude them
    packaging {
        resources.excludes.addAll(
            listOf(
                "META-INF/DEPENDENCIES",
                "META-INF/NOTICE",
                "META-INF/LICENSE",
                "META-INF/NOTICE",
                "META-INF/INDEX.LIST",
                "META-INF/AL2.0",
                "META-INF/LGPL2.1",
                "META-INF/ASL2.0",
                "META-INF/licenses/ASM",
                "META-INF/*.kotlin_module",
                "**/*.txt",
                "**/*.xml",
                "**/*.md",
                "**/*.properties",
            )
        )
    }
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
    implementation(libs.findBundle("android-core").get())
    implementation(libs.findBundle("koin").get())
    implementation(libs.findBundle("ui").get())

    testImplementation(platform(libs.findLibrary("junit-bom").get()))
    testImplementation(libs.findBundle("test").get())

    testRuntimeOnly(libs.findBundle("test-runtime").get())
}
plugins {
    id("com.android.library")
    kotlin("android")
    id("dev.drewhamilton.poko")
    id("configured-detekt")
}

internal val Project.libs: VersionCatalog
    get() =
        project.extensions.getByType<VersionCatalogsExtension>().named("libs")

android {
    compileSdk = 33
    defaultConfig {
        minSdk = 26

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
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
                "**/kotlin/**",
                "**/*.txt",
                "**/*.xml",
                "**/*.md",
                "**/*.properties",
            )
        )
    }
}

dependencies {
    implementation(platform(libs.findLibrary("kotlin-bom").get()))
    implementation(libs.findBundle("android-core").get())
    implementation(libs.findBundle("koin").get())
    implementation(libs.findBundle("ui").get())

    testImplementation(platform(libs.findLibrary("junit5-bom").get()))
    testImplementation(libs.findBundle("test").get())

    testRuntimeOnly(libs.findBundle("test-runtime").get())

    androidTestImplementation(libs.findBundle("test-instrumented").get())
}
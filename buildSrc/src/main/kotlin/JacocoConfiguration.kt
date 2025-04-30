import org.gradle.api.Project
import org.gradle.api.file.FileCollection

class JacocoConfiguration(private val project: Project) {

    private val excludesForJacoco = setOf(
        "**/R.class",
        "**/R$*.class",
        "**/BuildConfig.*",
        "**/Manifest*.*",
        "**/*Test*.*",
        "android/**/*.*",
        "androidx/**/*.*",
        "**/*Binding*.*",
        "**/*BR*.*",
        "**/DataBinderMapperImpl*.*",
        "**/R2.class",
        "**/R2$*.class",
        "**/*_ViewBinding*.*",
        "**/*Dagger*Component*.*",
        "**/*_GeneratedInjector.*",
        "**/*Hilt_*.*",
        "**/*Module.*",
        "**/*Module$*.*",
        "**/*MembersInjector*.*",
        "**/*_Factory*.*",
        "**/*Provide*Factory*.*",
        "**/*$*$*.*",
        "**/*Companion*.*",
        "**/*\$Lambda$*.*",

        "**/ComposableSingletons*.*",

        "**/*Application.*",
        "**/*Activity*.*",
        "**/*Fragment*.*",
        "**/*Receiver*.*",
        "**/*Service*.*",
        "**/*Adapter*.*",
        "**/*ViewHolder*.*",
        "**/*Binder*.*",

        "**/*Constants.*",
        "**/*Exception*.*",

        "**/*Dao_Impl*.*",
        "**/*Database_Impl*.*"
    )

    val sourceDirectoriesTree: FileCollection
        get() {
            val baseDir = project.layout.projectDirectory
            return project.files(
                "$baseDir/src/main/java",
                "$baseDir/src/main/kotlin",
                "$baseDir/src/debug/java",
                "$baseDir/src/debug/kotlin",
            )
        }

    val classDirectoriesTree = project.fileTree(project.layout.buildDirectory) {
        include(
            "**/classes/**/main/**",
            "**/intermediates/classes/debug/**",
            "**/tmp/kotlin-classes/debug/**"
        )
        exclude(excludesForJacoco)
    }

    val executionDatasTree = project.fileTree(project.layout.buildDirectory) {
        include("outputs/unit_test_code_coverage/debugUnitTest/testDebugUnitTest.exec")
        include("outputs/code_coverage/debugAndroidTest/connected/**/*.ec")
        include("jacoco/test.exec")
        include("jacoco/testDebugUnitTest.exec")
    }
}

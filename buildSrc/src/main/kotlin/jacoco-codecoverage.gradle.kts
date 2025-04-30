plugins {
    id("jacoco")
}

internal val Project.libs: VersionCatalog
    get() = project.extensions.getByType<VersionCatalogsExtension>().named("libs")

abstract class CoverageVerificationExtension {
    var minimum: BigDecimal = "0.8".toBigDecimal()
}

private val coverageVerificationExtension = extensions.create<CoverageVerificationExtension>("coverageVerification")

jacoco {
    toolVersion = libs.findVersion("jacoco").get().requiredVersion
}

private val isAndroidModule: Boolean
    get() = project.hasProperty("android")

private val testDependency: String
    get() {
        var testDependency = "test"
        if (isAndroidModule) {
            testDependency = "testDebugUnitTest"
        }
        return testDependency
    }

private val configuration = JacocoConfiguration(project)
private val classDirectoriesTree = configuration.classDirectoriesTree
private val sourceDirectoriesTree = configuration.sourceDirectoriesTree
private val executionDatasTree = configuration.executionDatasTree

tasks.register<JacocoReport>("codeCoverageReport") {
    dependsOn(testDependency)

    group = "Reporting"
    description = "Generate Jacoco coverage reports for $project."

    reports {
        xml.required = true
        html.required = true
        csv.required = true
    }

    classDirectories.setFrom(classDirectoriesTree)
    sourceDirectories.setFrom(sourceDirectoriesTree)
    executionData.setFrom(executionDatasTree)
}

tasks.register<JacocoCoverageVerification>("coverageVerification") {
    dependsOn("codeCoverageReport")

    group = "Verification"
    description = "Verify code coverage for $project."

    violationRules {
        rule {
            limit {
                minimum = coverageVerificationExtension.minimum
            }
        }
    }

    classDirectories.setFrom(classDirectoriesTree)
    sourceDirectories.setFrom(sourceDirectoriesTree)
    executionData.setFrom(executionDatasTree)
}

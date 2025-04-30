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

private val classDirectoriesTree = mutableListOf<FileTree>()
private val sourceDirectoriesTree = mutableListOf<FileCollection>()
private val executionDatasTree = mutableListOf<FileTree>()

tasks.register<JacocoReport>("rootCodeCoverageReport") {
    subprojects {
        val project = this
        project.plugins.withId("jacoco-codecoverage") {
            dependsOn("${project.name}:codeCoverageReport")
        }
    }

    group = "Reporting"
    description = "Generate overall Jacoco coverage report."

    reports {
        xml.required = true
        html.required = true
        csv.required = true
    }

    subprojects {
        val project = this
        project.plugins.withId("jacoco-codecoverage") {
            val configuration = JacocoConfiguration(project)
            classDirectoriesTree.add(configuration.classDirectoriesTree)
            sourceDirectoriesTree.add(configuration.sourceDirectoriesTree)
            executionDatasTree.add(configuration.executionDatasTree)
        }
    }

    classDirectories.setFrom(classDirectoriesTree)
    sourceDirectories.setFrom(sourceDirectoriesTree)
    executionData.setFrom(executionDatasTree)
}

tasks.register<JacocoCoverageVerification>("rootCoverageVerification") {
    dependsOn("rootCodeCoverageReport")

    group = "Verification"
    description = "Verify overall code coverage."

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

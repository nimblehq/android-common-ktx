import org.gradle.api.tasks.testing.Test

plugins {
    id("com.android.application") version "8.7.3" apply false
    id("com.android.library") version "8.7.3" apply false
    id("org.jetbrains.kotlin.android") version "2.0.21" apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.8"
    jacoco
}

detekt {
    config.setFrom(files("config/detekt.yml"))
    source.setFrom(
        files(
            "app/src/main/java",
            subprojects
                .map { module -> "${module.projectDir}/src/main/java" }
        )
    )

    parallel = false
    buildUponDefaultConfig = false
    disableDefaultRuleSets = false

    debug = false
    ignoreFailures = false

    ignoredBuildTypes = listOf("release")
    ignoredFlavors = listOf("production")
}

jacoco {
    toolVersion = "0.8.12"
}

val fileGenerated = listOf(
    "android/**/*.*",
    "**/R.class",
    "**/R\$*.class",
    "**/*\$ViewBinder*.*",
    "**/*\$InjectAdapter*.*",
    "**/*Injector*.*",
    "**/BuildConfig.*",
    "**/Manifest*.*",
    "**/*_ViewBinding*.*",
    "**/*_Factory*.*",
    "**/app/ui/screens/**/*DiffCallback*.*",
    "**/*Test*.*",
    // navigation component
    "**/*FragmentArgs*",
    "**/*FragmentDirections*",
    "**/FragmentNavArgsLazy.kt",
    "**/*Fragment*navArgs*",
    "**/screens/common/StartFragment.*",
    // kotlin enum Creator
    "**/*\$Creator*"
)

val packagesExcluded = listOf(
    "co/nimblehq/extensions/app/**",
    "com/bumptech/glide"
)

val fileFilter = fileGenerated + packagesExcluded

tasks.register<JacocoReport>("jacocoTestReport") {
    group = "Reporting"
    description = "Generate Jacoco coverage reports for Debug build"

    dependsOn(":app:testDebugUnitTest")
    dependsOn(":common-ktx:testDebugUnitTest")

    classDirectories.setFrom(
        fileTree("${project.rootDir}/app/build/intermediates/javac/debug/classes") {
            exclude(fileFilter)
        },
        fileTree("${project.rootDir}/common-ktx/build/intermediates/javac/debug/classes") {
            exclude(fileFilter)
        },
        fileTree("${project.rootDir}/app/build/tmp/kotlin-classes/debug") {
            exclude(fileFilter)
        },
        fileTree("${project.rootDir}/common-ktx/build/tmp/kotlin-classes/debug") {
            exclude(fileFilter)
        }
    )

    sourceDirectories.setFrom(
        files(
            "${project.rootDir}/app/src/main/java",
            "${project.rootDir}/common-ktx/src/main/java"
        )
    )

    executionData.setFrom(
        fileTree(project.rootDir) {
            include(
                "app/build/jacoco/testDebugUnitTest.exec",
                "common-ktx/build/jacoco/testDebugUnitTest.exec"
            )
        }
    )

    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}

tasks.withType<Test> {
    testLogging {
        events("passed", "skipped", "failed")
    }
}

import org.gradle.api.tasks.testing.Test

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.detekt)
    jacoco
}

detekt {
    toolVersion = libs.versions.detekt.get()

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
    toolVersion = libs.versions.jacoco.get()
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

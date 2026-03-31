import kotlinx.kover.gradle.plugin.dsl.KoverProjectExtension

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kover) apply false
    alias(libs.plugins.detekt)
}

dependencies {
    detektPlugins(libs.detekt.formatting)
}

detekt {
    toolVersion = libs.versions.detekt.get()

    config.setFrom(files("config/detekt.yml"))
    source.setFrom(
        files(subprojects.map { module -> "${module.projectDir}/src/main/java" })
    )

    parallel = false
    buildUponDefaultConfig = false
    disableDefaultRuleSets = false

    debug = false
    ignoreFailures = false

    ignoredBuildTypes = listOf("release")
    ignoredFlavors = listOf("production")
}

subprojects {
    apply(plugin = "org.jetbrains.kotlinx.kover")
    configure<KoverProjectExtension> {
        reports {
            filters {
                excludes {
                    androidGeneratedClasses()
                    annotatedBy(
                        "androidx.annotation.VisibleForTesting"
                    )
                    classes(
                        // View Binding & Data Binding
                        "**/*_ViewBinding*",
                        "**/*_Factory*",
                        "**/*\$ViewBinder*",
                        "**/*\$InjectAdapter*",
                        "**/*Injector*",
                        "*.*_ComponentTreeDeps*",
                        "*.*_HiltComponents*",
                        "*.*_MembersInjector*",
                        "*\$InstanceHolder",
                        // Navigation Component
                        "**/*FragmentArgs*",
                        "**/*FragmentDirections*",
                        "**/FragmentNavArgsLazy*",
                        "**/*Fragment*navArgs*",
                        "**/screens/common/StartFragment*",
                        // DiffCallback
                        "**/app/ui/screens/**/*DiffCallback*",
                        // Kotlin Enum Creator
                        "*.*\$Creator*",
                        // Test files
                        "**/*Test*"
                    )
                    packages(
                        "co.nimblehq.extensions.app",
                        "com.bumptech.glide"
                    )
                }
            }
        }
    }
}

tasks.withType<Test> {
    testLogging {
        events("passed", "skipped", "failed")
    }
}

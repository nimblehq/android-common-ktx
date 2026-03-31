import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("maven-publish")
}

android {
    namespace = "co.nimblehq.common.extensions"

    compileSdk = libs.versions.androidCompileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.androidMinSdk.get().toInt()
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        compose = true
    }

    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_1_8)
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.gson)

    implementation(platform(libs.compose.bom))
    implementation(libs.compose.foundation)

    androidTestImplementation(libs.test.ext.junit)
    androidTestImplementation(libs.test.espresso.core)
    androidTestImplementation(libs.test.hamcrest)

    testImplementation(libs.test.junit)
    testImplementation(libs.test.robolectric)
    testImplementation(platform(libs.compose.bom))
    testImplementation(libs.compose.ui.test.junit4)

    debugImplementation(libs.compose.ui.test.manifest)
}

afterEvaluate {
    publishing {
        publications {
            // Creates a Maven publication called "release".
            create<MavenPublication>("release") {
                // Applies the component for the release build variant.
                from(components["release"])

                // You can then customize attributes of the publication as shown below.
                groupId = "co.nimblehq"
                artifactId = "extensions"
                version = libs.versions.libVersionName.get()
            }
        }
    }
}

kover {
    currentProject {
        createVariant("custom") {
            add("debug")
        }
    }
}

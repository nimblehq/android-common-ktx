import java.util.Properties

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
    id("io.gitlab.arturbosch.detekt")
}

val versionPropertiesFile = rootProject.file("version.properties")
val versionProperties = Properties().apply {
    load(versionPropertiesFile.inputStream())
}

android {
    namespace = "co.nimblehq.common"

    compileSdk = 35

    defaultConfig {
        minSdk = 21

        // versionCode/versionName removed - not needed for libraries
        // Version managed in version.properties for publishing

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

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.6.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("com.google.android.material:material:1.4.0")
    implementation("com.google.code.gson:gson:2.8.9")

    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("org.hamcrest:hamcrest-library:1.3")

    testImplementation("junit:junit:4.13.2")

    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.8")
}

// Detekt configuration (inlined from config/detekt.gradle)
detekt {
    config.setFrom(files("$rootDir/config/detekt.yml"))
    buildUponDefaultConfig = true
    allRules = false
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
                version = versionProperties.getProperty("versionName")
            }
        }
    }
}

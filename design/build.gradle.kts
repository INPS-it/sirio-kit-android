import org.gradle.jvm.tasks.Jar

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.dokka)
    id("maven-publish")
    id("kotlin-parcelize")
}

android {
    namespace = "it.inps.sirio"
    compileSdk = libs.versions.sdkCompile.get().toInt()

    defaultConfig {
        minSdk = libs.versions.sdkMin.get().toInt()

        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            isShrinkResources = false
        }
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }

    packaging {
        resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }

    publishing {
        singleVariant("release")
    }
}

kotlin {
    jvmToolchain(17)
}

// --- Sources Jar (KDoc inline nell'IDE) ---
val androidSourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from("src/main/java")
    from("src/main/kotlin")
}

val androidJavadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")

    // Dokka V2: genera la publication HTML
    dependsOn(tasks.named("dokkaGeneratePublicationHtml"))

    // Output HTML
    from(layout.buildDirectory.dir("dokka/html"))
}

apply(from = "$rootDir/gradle/publish_github_package.gradle")

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.material)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.coil)
    implementation(libs.bundles.design)
    api(libs.bundles.fontawesome.compose)
}

plugins {
//    alias(libs.plugins.android.application)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
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
    kotlin {
        jvmToolchain(17)
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
    packaging {
        resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }
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

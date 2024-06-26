plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "it.inps.design.sample"
    compileSdk = libs.versions.sdkCompile.get().toInt()

    defaultConfig {
        applicationId = "it.inps.design.sample"
        minSdk = libs.versions.sdkMin.get().toInt()
        targetSdk = libs.versions.sdkTarget.get().toInt()
        versionCode = 8
        versionName = "8.0.0"

        vectorDrawables {
            useSupportLibrary = true
        }
        signingConfig = signingConfigs.getByName("debug")
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

dependencies {
    implementation(project(":design"))
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.demo)
    implementation(libs.bundles.appcompat)
    implementation(libs.bundles.material)
    implementation(libs.bundles.compose)
}

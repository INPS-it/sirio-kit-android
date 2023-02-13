plugins {
    id("com.android.library")
    id("kotlin-android")
    id("maven-publish")
}

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            isShrinkResources = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    namespace = "it.inps.sirio"
}

val compose_alpha = "1.3.0"
val compose_version = "1.2.0"
val nav_version = "2.5.1"

dependencies {
//    val composeBom = platform("androidx.compose:compose-bom:2022.11.00")
//    implementation(composeBom)
//    androidTestImplementation(composeBom)

    implementation("com.google.android.material:material:1.7.0")
    implementation("androidx.compose.ui:ui:$compose_alpha")
    implementation("androidx.compose.material:material:$compose_alpha")
    implementation("androidx.compose.ui:ui-tooling-preview:$compose_alpha")
    implementation("androidx.navigation:navigation-compose:$nav_version")
    implementation("androidx.compose.material3:material3:1.0.1")
    implementation("com.google.accompanist:accompanist-flowlayout:0.27.0")
    debugImplementation("androidx.compose.ui:ui-tooling:$compose_alpha")
    api("com.github.Gurupreet:FontAwesomeCompose:1.1.0")

//Workaround
    debugImplementation("androidx.customview:customview:1.2.0-alpha02")
    debugImplementation("androidx.customview:customview-poolingcontainer:1.0.0")
}

apply(from = "$rootDir/gradle/publish_github_package.gradle")

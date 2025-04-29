plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("dagger.hilt.android.plugin")
    //alias(libs.plugins.ksp)
    id("com.google.devtools.ksp")
    alias(libs.plugins.hilt.android)
    id("kotlin-parcelize")
    alias(libs.plugins.compose.compiler)

   // id("com.google.devtools.ksp")
   // kotlin("kapt")
}

android {
    namespace = "com.example.newsapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.newsapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        compose = true
    }
}


composeCompiler {
   // reportsDestination = layout.buildDirectory.dir("compose_compiler")
    //stabilityConfigurationFile = rootProject.layout.projectDirectory.file("stability_config.conf")
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.material)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
     ksp(libs.androidx.hilt.compiler)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.accompanist.swiperefresh)
    implementation(libs.glide)
    ksp(libs.compiler)
    implementation(libs.kotlinx.metadata.jvm)
    implementation(libs.logging.interceptor)
    implementation(libs.okhttp)
    ksp(libs.symbol.processing.api)
    implementation(libs.androidx.material.icons.extended)

    implementation("androidx.media3:media3-exoplayer:1.2.0")
    implementation("androidx.media3:media3-ui:1.2.0")


}
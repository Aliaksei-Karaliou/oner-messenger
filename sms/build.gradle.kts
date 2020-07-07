import com.github.aliakseikaraliou.oner.buildSrc.Dependencies.Android
import com.github.aliakseikaraliou.oner.buildSrc.Dependencies.DAGGER
import com.github.aliakseikaraliou.oner.buildSrc.Dependencies.Kotlin
import com.github.aliakseikaraliou.oner.buildSrc.KAPT
import com.github.aliakseikaraliou.oner.buildSrc.Project

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(30)
    buildToolsVersion = "29.0.3"

    defaultConfig {
        applicationId = "com.github.aliakseikaraliou.oner"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(project(Project.BASE))

    implementation(Kotlin.STDLIB)

    implementation(Android.CORE)
    implementation(Android.APPCOMPAT)
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")

    implementation(DAGGER)
    kapt(KAPT.DAGGER)

    testImplementation("junit:junit:4.13")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")

}
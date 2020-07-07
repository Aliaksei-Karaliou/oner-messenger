package com.github.aliakseikaraliou.oner.buildSrc

object Dependencies {
    object Android {
        const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.ANDROID_APP_COMPAT}"
        const val CORE = "androidx.core:core-ktx:${Versions.ANDROID_CORE}"
    }

    object Kotlin {
        const val STDLIB = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN}"
    }

    const val DAGGER = "com.google.dagger:dagger:${Versions.DAGGER}"
}

object KAPT {
    const val DAGGER = "com.google.dagger:dagger-compiler:${Versions.DAGGER}"
}

object Project {
    const val BASE = ":base"
}

object Versions {
    internal const val ANDROID_APP_COMPAT = "1.1.0"
    internal const val ANDROID_CORE = "1.3.0"
    const val KOTLIN = "1.3.72"
    internal const val DAGGER = "2.28.1"
}
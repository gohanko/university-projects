// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false

    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    id("com.google.devtools.ksp") version "1.9.0-1.0.12"
}

buildscript {
    dependencies {
        classpath(libs.hilt.android.gradle.plugin)
    }
}
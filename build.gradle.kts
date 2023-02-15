buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.4.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")
    }
}// Top-level build file where you can add configuration options common to all sub-projects/modules.

subprojects {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    id("com.github.ben-manes.versions") version "0.43.0"
    id("nl.littlerobots.version-catalog-update") version "0.7.0"
//    id 'com.android.application' version '7.4.1' apply false
//    id 'com.android.library' version '7.4.1' apply false
//    id 'org.jetbrains.kotlin.android' version '1.7.0' apply false
}

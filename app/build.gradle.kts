plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id("kotlin-android")
    id("kotlin-parcelize")
}

android {
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }

    compileSdk = 33

    defaultConfig {
        applicationId = "gg.lol.android"
        minSdk = 29
        targetSdk = 33
        versionCode = 1
        versionName = "1.0.0"
        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")

            buildConfigField("String", "BASE_URL", "\"" + Config.Debug.BASEURL + "\"")
        }

        create("staging") {
            isMinifyEnabled = true
            signingConfig = signingConfigs.getByName("debug")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            buildConfigField("String", "BASE_URL", "\"" + Config.Debug.BASEURL + "\"")
        }

        getByName("release") {
            isMinifyEnabled = true
            signingConfig = signingConfigs.getByName("debug")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            buildConfigField("String", "BASE_URL", "\"" + Config.Release.BASEURL + "\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.2"
//        kotlinCompilerVersion = "1.7.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.compose.foundation:foundation:1.3.1")
    implementation("androidx.compose.foundation:foundation-layout:1.3.1")
    implementation("androidx.navigation:navigation-compose:2.5.3")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("androidx.compose.ui:ui:1.3.3")
    implementation("androidx.compose.ui:ui-tooling:1.3.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.3.3")
    implementation("androidx.compose.material3:material3:1.0.1")
    implementation("androidx.compose.runtime:runtime-livedata:1.3.3")
    // 이거쓰면 ROOM DB 에러남
//    implementation("androidx.work:work-runtime:2.8.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")

    // Coli
    implementation("io.coil-kt:coil-compose:2.2.2")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.45")
    kapt("com.google.dagger:hilt-android-compiler:2.45")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    //

    // Room DB
    implementation("androidx.room:room-runtime:2.4.3")
    implementation("androidx.room:room-ktx:2.4.3")
    kapt("androidx.room:room-compiler:2.4.3")
    //

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.3.3")
    debugImplementation("androidx.compose.ui:ui-tooling:1.3.3")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.3.3")

    // AndroidX Test Core library
    testImplementation("androidx.test:core:1.5.0")

    // AndroidX Test Runner library
    testImplementation("androidx.test:runner:1.5.2")

    val mockkVersion = "1.12.0"
    // MockK library for mocking objects
    testImplementation("io.mockk:mockk:1.9.3")

    // AndroidX Test Rules library
    testImplementation("androidx.test:rules:1.5.0")

    // Coroutines Test library for testing with coroutines
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.2")
    testImplementation("androidx.arch.core:core-testing:2.2.0")

    testImplementation("androidx.test.ext:junit-ktx:1.1.5")
    testImplementation("androidx.test:core-ktx:1.5.0")
    testImplementation("org.robolectric:robolectric:4.9.2")
}
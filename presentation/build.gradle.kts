plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    namespace = "gg.op.lol.presentation"
    compileSdk = 33

    defaultConfig {
        minSdk = 26
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    /*kotlinOptions {
        jvmTarget = "1.8"
    }*/
}

dependencies {
    implementation(project(Modules.domain))

    implementation(AndroidX.FOUNDATION)
    implementation(AndroidX.FOUNDATION_LAYOUT)

    implementation(AndroidX.NAVIGATION_COMPOSE)
    implementation(AndroidX.CORE_KTX)
    implementation(AndroidX.ACTIVITY_COMPOSE)
    implementation(AndroidX.COMPOSE_UI)
    implementation(AndroidX.COMPOSE_UI_TOOLING)
    implementation(AndroidX.COMPOSE_UI_TOOLING_PREVIEW)
    implementation(AndroidX.MATERIAL3)
    implementation(AndroidX.RUNTIME_LIVEDATA)
    implementation(AndroidX.LIFECYCLE_LIVEDATA_KTX)
    implementation(AndroidX.LIFECYCLE_RUNTIME_KTX)

    implementation(AndroidX.PAGING_RUNTIME_KTX)

//    implementation(org.gradle.platform.base.Library.COIL_COMPOSE)

    implementation(Google.HILT_ANDROID)
    kapt(Google.HILT_ANDROID_COMPILER)
    implementation(AndroidX.HILT_NAVIGATION_COMPOSE)

    implementation(AndroidX.ROOM_RUNTIME)
    implementation(AndroidX.ROOM_KTX)
    kapt(AndroidX.ROOM_COMPILER)

//    implementation(org.gradle.platform.base.Library.RETROFIT)
//    implementation(org.gradle.platform.base.Library.RETROFIT_GSON)

    androidTestImplementation(AndroidTest.JUNIT_EXT)
    androidTestImplementation(AndroidTest.ESPRESSO_CORE)
    androidTestImplementation(AndroidTest.COMPOSE_UI_TEST)
    debugImplementation(AndroidTest.COMPOSE_UI_TOOLING)
    debugImplementation(AndroidTest.COMPOSE_UI_TEST_MANIFEST)

    testImplementation(UnitTest.JUNIT)
    testImplementation(UnitTest.CORE_TEST)
    testImplementation(UnitTest.RUNNER)
    testImplementation(UnitTest.MOCKK)
    testImplementation(UnitTest.RULES)
    testImplementation(UnitTest.KOTLINX_COROUTINES_TEST)
    testImplementation(UnitTest.CORE_TESTING)
    testImplementation(UnitTest.JUNIT_KTX)
    testImplementation(UnitTest.CORE_KTX)
    testImplementation(UnitTest.ROBOLECTRIC)
}

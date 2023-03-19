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

    compileSdk = Config.Android.COMPILESDK

    defaultConfig {
        minSdk = Config.Android.MINSDK
        targetSdk = Config.Android.TARGETSDK

        applicationId = Config.Release.APPLICATION_ID
        versionCode = Config.Release.VERSION_CODE
        versionName = Config.Release.VERSION_NAME

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
        kotlinCompilerExtensionVersion = Config.ComposeOption.KOTLIN_COMPILER_EXTENSION_VERSION
    }
}

dependencies {
    implementation(project(Modules.presentation))
    implementation(project(Modules.domain))
    implementation(project(Modules.data))
    implementation(project(Modules.remote))
    implementation(project(Modules.local))

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

    implementation(Library.COIL_COMPOSE)

    implementation(Google.HILT_ANDROID)
    kapt(Google.HILT_ANDROID_COMPILER)
    implementation(AndroidX.HILT_NAVIGATION_COMPOSE)

//    implementation(Google.HILT_ANDROID)
//    kapt(Google.HILT_COMPILER)
//    implementation(Google.HILT_ANDROID_COMPILER)
//    implementation(AndroidX.HILT_NAVIGATION_COMPOSE)

    implementation(AndroidX.ROOM_RUNTIME)
    implementation(AndroidX.ROOM_KTX)
    kapt(AndroidX.ROOM_COMPILER)

    implementation(Library.RETROFIT)
    implementation(Library.RETROFIT_GSON)

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

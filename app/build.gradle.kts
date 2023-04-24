plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id("kotlin-android")
    id("kotlin-parcelize")
}

android {
    namespace = "gg.lol.android"
    testOptions {
        unitTests {
            all {
                it.useJUnitPlatform()
            }
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

            buildConfigField("String", "KR_URL", "\"" + Config.Debug.KR_URL + "\"")
            buildConfigField("String", "ASIA_URL", "\"" + Config.Debug.ASIA_URL + "\"")
            buildConfigField("String", "DDRAGON_URL", "\"" + Config.Release.DDRAGON_URL + "\"")
        }
        getByName("release") {
            isMinifyEnabled = true
            signingConfig = signingConfigs.getByName("debug")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            buildConfigField("String", "KR_URL", "\"" + Config.Release.KR_URL + "\"")
            buildConfigField("String", "ASIA_URL", "\"" + Config.Release.ASIA_URL + "\"")
            buildConfigField("String", "DDRAGON_URL", "\"" + Config.Release.DDRAGON_URL + "\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Config.ComposeOption.KOTLIN_COMPILER_EXTENSION_VERSION
    }
}

dependencies {
    implementation(project(Modules.domain))
    implementation(project(Modules.data))

    implementation(KotlinDep.KOTLIN_STDLIB)
    implementation(KotlinDep.KOTLIN_REFLECT)

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

//    implementation(AndroidX.PAGING_COMMON_KTX)
    implementation(AndroidX.PAGING_COMPOSE)

    implementation(OtherLibrary.COIL_COMPOSE)

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

    implementation(OtherLibrary.RETROFIT)
    implementation(OtherLibrary.RETROFIT_GSON)

    implementation(OtherLibrary.RETROFIT_GSON)
    implementation(OtherLibrary.RECORDERABLE)

    debugImplementation(OtherLibrary.LEAKCANARY)

    debugImplementation(OtherLibrary.FLIPPER)
    debugImplementation(OtherLibrary.FLIPPER_NETWORK)
    debugImplementation(OtherLibrary.FLIPPER_SOLOADER)
    debugImplementation(OtherLibrary.FLIPPER_LEAKCANARY)
    releaseImplementation(OtherLibrary.FLIPPER_NOOP)

    androidTestImplementation(AndroidTest.JUNIT_EXT)
    androidTestImplementation(AndroidTest.ESPRESSO_CORE)
    androidTestImplementation(AndroidTest.COMPOSE_UI_TEST)
    androidTestImplementation(UnitTest.HILT_TEST)
    androidTestImplementation(Google.HILT_ANDROID_COMPILER)
    kaptTest(Google.HILT_ANDROID_COMPILER)

    debugImplementation(AndroidTest.COMPOSE_UI_TOOLING)
    debugImplementation(AndroidTest.COMPOSE_UI_TEST_MANIFEST)

//    testImplementation(UnitTest.JUNIT)
//    testImplementation(UnitTest.JUNIT_EXT)
//    testImplementation(UnitTest.JUNIT_KTX)
    testImplementation(UnitTest.CORE_TEST)
    testImplementation(UnitTest.RUNNER)
//    testImplementation(UnitTest.MOCKK)
    testImplementation(UnitTest.RULES)
    testImplementation(UnitTest.KOTLINX_COROUTINES_TEST)
    testImplementation(UnitTest.CORE_TESTING)
    testImplementation(UnitTest.CORE_KTX)
    testImplementation(UnitTest.ROBOLECTRIC)
    testImplementation(UnitTest.HILT_TEST)

    testImplementation("io.mockk:mockk:1.13.5")
    testImplementation("io.kotest:kotest-runner-junit5:5.6.1")
    testImplementation("io.kotest:kotest-assertions-core:5.6.1")
    testImplementation("io.kotest:kotest-property:5.6.1")
}

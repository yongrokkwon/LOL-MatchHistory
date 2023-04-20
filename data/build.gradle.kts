import java.util.Properties

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

val properties = Properties()
properties.load(project.rootProject.file("local.properties").inputStream())

android {
    namespace = "gg.op.lol"
    compileSdk = Config.Android.COMPILESDK

    defaultConfig {
        buildConfigField("String", "API_KEY", properties["API_KEY"].toString())
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(project(Modules.domain))

    implementation(AndroidX.ROOM_RUNTIME)
    implementation(AndroidX.ROOM_KTX)
    kapt(AndroidX.ROOM_COMPILER)

    implementation(OtherLibrary.RETROFIT)
    implementation(OtherLibrary.RETROFIT_GSON)
    implementation(OtherLibrary.OKHTTP_LOGGING)

    implementation(CoroutinesDep.COROUTINE_CORE)
    implementation(JavaDep.JAVAX_INJECT)
    implementation(AndroidX.PAGING_COMMON_KTX)

    implementation(CoroutinesDep.COROUTINE_CORE)
    implementation(CoroutinesDep.COROUTINE_ANDROID)
    debugImplementation(OtherLibrary.FLIPPER)
    debugImplementation(OtherLibrary.FLIPPER_SOLOADER)
    releaseImplementation(OtherLibrary.FLIPPER_NOOP)
    debugImplementation(OtherLibrary.FLIPPER_NETWORK)
}

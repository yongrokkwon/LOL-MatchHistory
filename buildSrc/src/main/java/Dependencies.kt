object Versions {
    const val FOUNDATION_VERSION = "1.3.1"
    const val NAVIGATION_COMPOSE_VERSION = "2.5.3"
    const val CORE_KTX_VERSION = "1.9.0"
    const val LIFECYCLE_VERSION = "2.6.0"
    const val ACTIVITY_COMPOSE_VERSION = "1.6.1"
    const val COMPOSE_VERSION = "1.3.3"
    const val MATERIAL3_VERSION = "1.0.1"

    const val COIL_COMPOSE_VERSION = "2.2.2"
    const val HILT_VERSION = "2.45"
    const val HILT_COMPILER_VERSION = "1.0.0"
    const val HILT_NAVIGATION_COMPOSE_VERSION = "1.0.0"

    const val ROOM_VERSION = "2.4.3"
    const val RETROFIT_VERSION = "2.9.0"
    const val FILPPER = "0.189.0"
    const val SOLOADER = "0.10.5"
    const val LEAK_CARNARY = "2.8.1"

    const val CORE_TEST_VERSION = "1.5.0"
    const val RUNNER_VERSION = "1.5.2"
    const val MOCKK_VERSION = "1.13.5"
    const val KOTLINX_COROUTINES_TEST_VERSION = "1.6.4"
    const val CORE_TESTING_VERSION = "2.2.0"
    const val TEST_CORE_KTX_VERSION = "1.5.0"
    const val ROBOLECTRIC_VERSION = "4.9.2"

    const val ESPRESSO_CORE = "3.5.1"
    const val COMPOSE_UI = "1.3.3"
    const val PAGING_COMPOSE = "1.0.0-alpha18"
    const val PAGING_KTX = "3.2.0-alpha04"

    const val JUNIT_VERSION = "4.13.2"
    const val JUNIT_EXT = "1.1.5"
    const val JUNIT_KTX_VERSION = "1.1.5"

    const val KOTLIN = "1.8.0"
    const val JAVAX_INJECT = "1"

    const val COROUTINE_CORE = "1.6.4"
    const val COROUTINE_ANDROID = "1.6.4"

    const val OKHTTP_LOGGING = "4.10.0"

    const val RECORDERABLE = "0.9.6"

    const val KOTEST = "5.6.1"
}

object CoroutinesDep {
    const val COROUTINE_CORE =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINE_CORE}"
    const val COROUTINE_ANDROID =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINE_ANDROID}"
}

object KotlinDep {
    const val KOTLIN_STDLIB = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN}"
    const val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect:${Versions.KOTLIN}"
}

object JavaDep {
    const val JAVAX_INJECT = "javax.inject:javax.inject:${Versions.JAVAX_INJECT}"
}

object AndroidX {
    const val FOUNDATION =
        "androidx.compose.foundation:foundation:${Versions.FOUNDATION_VERSION}"
    const val FOUNDATION_LAYOUT =
        "androidx.compose.foundation:foundation-layout:${Versions.FOUNDATION_VERSION}"

    const val NAVIGATION_COMPOSE =
        "androidx.navigation:navigation-compose:${Versions.NAVIGATION_COMPOSE_VERSION}"

    const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX_VERSION}"

    const val ACTIVITY_COMPOSE =
        "androidx.activity:activity-compose:${Versions.ACTIVITY_COMPOSE_VERSION}"

    const val COMPOSE_UI = "androidx.compose.ui:ui:${Versions.COMPOSE_VERSION}"
    const val COMPOSE_UI_TOOLING = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE_VERSION}"
    const val COMPOSE_UI_TOOLING_PREVIEW =
        "androidx.compose.ui:ui-tooling-preview:${Versions.COMPOSE_VERSION}"
    const val COMPOSE_RUNTIME_LIVEDATA =
        "androidx.compose.runtime:runtime-livedata:${Versions.COMPOSE_VERSION}"

    const val MATERIAL3 = "androidx.compose.material3:material3:${Versions.MATERIAL3_VERSION}"

    const val LIFECYCLE_LIVEDATA_KTX =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE_VERSION}"
    const val LIFECYCLE_RUNTIME_KTX =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE_VERSION}"
    const val PAGING_COMPOSE = "androidx.paging:paging-compose:${Versions.PAGING_COMPOSE}"
    const val PAGING_RUNTIME_KTX =
        "androidx.paging:paging-runtime-ktx:${Versions.PAGING_KTX}"
    const val PAGING_COMMON_KTX =
        "androidx.paging:paging-common-ktx:${Versions.PAGING_KTX}"

    const val HILT_NAVIGATION_COMPOSE =
        "androidx.hilt:hilt-navigation-compose:${Versions.HILT_NAVIGATION_COMPOSE_VERSION}"

    const val ROOM_RUNTIME = "androidx.room:room-runtime:${Versions.ROOM_VERSION}"
    const val ROOM_KTX = "androidx.room:room-ktx:${Versions.ROOM_VERSION}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${Versions.ROOM_VERSION}"
    const val ROOM_COMMON = "androidx.room:room-common:${Versions.ROOM_VERSION}"
}

object Google {
    const val HILT_ANDROID = "com.google.dagger:hilt-android:${Versions.HILT_VERSION}"
    const val HILT_ANDROID_COMPILER =
        "com.google.dagger:hilt-android-compiler:${Versions.HILT_VERSION}"
    const val HILT_COMPILER =
        "androidx.hilt:hilt-compiler:${Versions.HILT_COMPILER_VERSION}"
}

object OtherLibrary {
    const val FLIPPER = "com.facebook.flipper:flipper:${Versions.FILPPER}"
    const val FLIPPER_SOLOADER = "com.facebook.soloader:soloader:${Versions.SOLOADER}"
    const val FLIPPER_NOOP = "com.facebook.flipper:flipper-noop:${Versions.FILPPER}"
    const val FLIPPER_NETWORK = "com.facebook.flipper:flipper-network-plugin:${Versions.FILPPER}"
    const val FLIPPER_LEAKCANARY = "com.facebook.flipper:flipper-leakcanary2-plugin:${Versions.FILPPER}"
    const val LEAKCANARY = "com.squareup.leakcanary:leakcanary-android:${Versions.LEAK_CARNARY}"

    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT_VERSION}"
    const val RETROFIT_GSON =
        "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT_VERSION}"

    const val OKHTTP_LOGGING = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP_LOGGING}"

    const val COIL_COMPOSE = "io.coil-kt:coil-compose:${Versions.COIL_COMPOSE_VERSION}"

    const val RECORDERABLE = "org.burnoutcrew.composereorderable:reorderable:${Versions.RECORDERABLE}"
}

object UnitTest {
//    const val JUNIT = "org.junit.jupiter:junit-jupiter:${Versions.JUNIT_VERSION}"
    const val JUNIT = "junit:junit:${Versions.JUNIT_VERSION}"
    const val JUNIT_KTX = "androidx.test.ext:junit-ktx:${Versions.JUNIT_KTX_VERSION}"
    const val JUNIT_EXT = "androidx.test.ext:junit:${Versions.JUNIT_EXT}"
    const val CORE_TEST = "androidx.test:core:${Versions.CORE_TEST_VERSION}"
    const val RUNNER = "androidx.test:runner:${Versions.RUNNER_VERSION}"
    const val MOCKK = "io.mockk:mockk:${Versions.MOCKK_VERSION}"
    const val RULES = "androidx.test:rules:${Versions.CORE_TEST_VERSION}"
    const val KOTLINX_COROUTINES_TEST =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.KOTLINX_COROUTINES_TEST_VERSION}"
    const val CORE_TESTING = "androidx.arch.core:core-testing:${Versions.CORE_TESTING_VERSION}"
    const val CORE_KTX = "androidx.test:core-ktx:${Versions.TEST_CORE_KTX_VERSION}"
    const val ROBOLECTRIC = "org.robolectric:robolectric:${Versions.ROBOLECTRIC_VERSION}"
    const val HILT_TEST = "com.google.dagger:hilt-android-testing:${Versions.HILT_VERSION}"
    const val KOTEST_RUNNER_JUNIT = "io.kotest:kotest-runner-junit5:${Versions.KOTEST}"
    const val KOTEST_ASSERTIONS_CORE = "io.kotest:kotest-assertions-core:${Versions.KOTEST}"
    const val KOTEST_PROPERTY = "io.kotest:kotest-property:${Versions.KOTEST}"

}

object AndroidTest {
    const val JUNIT_EXT = "androidx.test.ext:junit:${Versions.JUNIT_EXT}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
    const val COMPOSE_UI_TEST = "androidx.compose.ui:ui-test-junit4:${Versions.COMPOSE_UI}"
    const val COMPOSE_UI_TOOLING = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE_UI}"
    const val COMPOSE_UI_TEST_MANIFEST =
        "androidx.compose.ui:ui-test-manifest:${Versions.COMPOSE_UI}"
}

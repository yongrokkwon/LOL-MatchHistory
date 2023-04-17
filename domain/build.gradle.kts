plugins {
    id("java-library")
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(CoroutinesDep.COROUTINE_CORE)
    implementation(CoroutinesDep.COROUTINE_ANDROID)
    implementation(AndroidX.PAGING_COMMON_KTX)

    implementation(JavaDep.JAVAX_INJECT)
}

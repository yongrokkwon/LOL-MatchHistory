plugins {
    id("java-library")
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    implementation(CoroutinesDep.COROUTINE_CORE)
    implementation(CoroutinesDep.COROUTINE_ANDROID)

    implementation(JavaDep.JAVAX_INJECT)
}

plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("kotlin-kapt")
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    implementation(project(Modules.domain))

    implementation(CoroutinesDep.COROUTINE_CORE)
    implementation(JavaDep.JAVAX_INJECT)
}

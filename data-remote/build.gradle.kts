plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    implementation(project(Modules.data))

    implementation(JavaDep.JAVAX_INJECT)

    implementation(Library.RETROFIT)
    implementation(Library.RETROFIT_GSON)
    implementation(Library.OKHTTP_LOGGING)
}
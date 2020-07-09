import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.3.61"
    application
}

group = "Contact Specification"
version = "1.0-SNAPSHOT"

val ktorVersion = "1.3.0"


repositories {
    mavenCentral()
    jcenter()
}


dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testCompile("junit", "junit", "4.12")

    // Align versions of all Kotlin components
    implementation("org.jetbrains.kotlin:kotlin-bom")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")


    // Arrow
    implementation("io.arrow-kt:arrow-core:0.10.4")
    implementation("io.arrow-kt:arrow-syntax:0.10.4")
    implementation("io.arrow-kt:arrow-fx:0.10.4")


    implementation("io.ktor", "ktor-server-core", "$ktorVersion")
    implementation("io.ktor", "ktor-server-netty", "$ktorVersion")

    testImplementation("io.ktor:ktor-client-mock:$ktorVersion")
    testImplementation("io.ktor:ktor-client-mock-jvm:$ktorVersion")

    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.6.4")


    compile ("org.jetbrains.exposed","exposed", "0.17.7")

    compile("mysql", "mysql-connector-java", "8.0.13")
    compile("org.slf4j" , "slf4j-simple", "1.7.25")
    compile("com.google.code.gson","gson","2.8.1")



}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
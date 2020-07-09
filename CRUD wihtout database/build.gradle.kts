import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.3.61"
    application
}

group = "CRUD wihtout database"
version = "1.0-SNAPSHOT"

val ktor_version = "1.3.0"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))



    testCompile("junit", "junit", "4.12")
    implementation("org.jetbrains.kotlin", "kotlin-stdlib-jdk8", "1.3.72")
    compile ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.72")
    compile ("io.arrow-kt:arrow-core:0.10.4")
    compile ("io.arrow-kt:arrow-syntax:0.10.4")
    compile("com.google.code.gson","gson","2.8.1")
    compile("io.ktor:ktor-gson:$ktor_version")
    compile ("org.slf4j","slf4j-api","1.6.6")
    implementation("io.ktor", "ktor-server-core", "$ktor_version")
    implementation("io.ktor", "ktor-server-netty", "$ktor_version")
    implementation ("io.ktor:ktor-client-cio:$ktor_version")
    compile ("org.modelmapper","modelmapper","2.3.8")
    compile("com.fasterxml.jackson.dataformat","jackson-dataformat-xml","2.11.0")






}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
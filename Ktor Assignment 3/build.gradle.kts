import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.3.61"
    application
}

group = "Ktor Assignment 3"
version = "1.0-SNAPSHOT"

val ktorVersion = "1.3.0"
//val exposedVersion = "0.21.1"


repositories {
    mavenCentral()
    jcenter()

    /*  maven {"https://kotlin.bintray.com/ktor"}
      maven {"https://dl.bintray.com/kotlin/kotlinx"}
      maven {"https://dl.bintray.com/kotlin/exposed"}   */
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testCompile("junit", "junit", "4.12")

    implementation("io.ktor", "ktor-server-core", "$ktorVersion")
    implementation("io.ktor", "ktor-server-netty", "$ktorVersion")

    testImplementation("io.ktor:ktor-client-mock:$ktorVersion")
    testImplementation("io.ktor:ktor-client-mock-jvm:$ktorVersion")

    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.6.4")
    /*compile("org.jetbrains.exposed", "exposed-core", exposedVersion)
    compile("org.jetbrains.exposed", "exposed-jdbc", exposedVersion)
    compile("org.jetbrains.exposed", "exposed-java-time", exposedVersion)*/

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
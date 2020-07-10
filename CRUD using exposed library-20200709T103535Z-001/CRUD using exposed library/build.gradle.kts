import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.3.61"
    application
}

group = "CRUD using exposed library"
version = "1.0-SNAPSHOT"


val ktorVersion = "1.3.0"

repositories {
    mavenCentral() // to fetch dependencies from maven central repo.
    jcenter() //It is the largest repository in the world for Java and Android 
    // OSS libraries, packages and components. It is public repo and is free to use.
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testCompile("junit", "junit", "4.12")

    implementation("io.ktor", "ktor-server-core", "$ktorVersion")
    implementation("io.ktor", "ktor-server-netty", "$ktorVersion") //his dependency provides a Netty web server and all the required code to run Ktor application on top of it:

    testImplementation("io.ktor:ktor-client-mock:$ktorVersion")
    testImplementation("io.ktor:ktor-client-mock-jvm:$ktorVersion")

    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.6.4")


    compile ("org.jetbrains.exposed","exposed", "0.17.7") //Exposed is a lightweight SQL library on top of JDBC driver for Kotlin language.

    compile("mysql", "mysql-connector-java", "8.0.13")//application and mysql connector
    compile("org.slf4j" , "slf4j-simple", "1.7.25")
    compile("com.google.code.gson","gson","2.8.1")

    compile("io.ktor:ktor-gson:$ktorVersion")

    //jackson dependency
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.8.+")

}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
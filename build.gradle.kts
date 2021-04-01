plugins {
    java
    kotlin("jvm") version "1.4.31"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.ktor:ktor-client-core:1.5.2")
    implementation("io.ktor:ktor-client-cio:1.5.2")
    implementation("io.ktor:ktor-client-mock:1.5.2")
    implementation("org.jsoup:jsoup:1.11.3")
    testImplementation("org.junit.jupiter", "junit-jupiter", "5.4.2")
    testImplementation("io.mockk", "mockk", "1.10.6")
}
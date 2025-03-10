plugins {
    kotlin("jvm") version "2.0.21"
}

group = "com.echoinacup"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // JUnit 5 API for writing tests
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    // JUnit 5 engine to run tests
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")
    // JUnit 5 Parameterized Tests
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.0")
    testImplementation("org.assertj:assertj-core:3.27.3")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
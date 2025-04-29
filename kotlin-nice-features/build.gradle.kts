plugins {
    kotlin("jvm") version "2.0.21"
}

group = "com.echoinacup"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}


dependencies {
    val log4jVersion = "2.24.3"
    val junitVersion = "5.10.0"
    val assertJVersion = "3.27.3"

    implementation("org.apache.logging.log4j:log4j-api:$log4jVersion")
    implementation("org.apache.logging.log4j:log4j-core:$log4jVersion")

    // JUnit 5 API for writing tests
    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
    // JUnit 5 Parameterized Tests
    testImplementation("org.junit.jupiter:junit-jupiter-params:${junitVersion}")
    // assertJ
    testImplementation("org.assertj:assertj-core:${assertJVersion}")
    // JUnit 5 engine to run tests
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
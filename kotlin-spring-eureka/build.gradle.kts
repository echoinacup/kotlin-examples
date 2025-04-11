plugins {
    kotlin("jvm") version "2.1.10"
    id("org.springframework.boot") version "3.4.4"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.echoinacup"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:2024.0.1")
    }
}

dependencies {
    // Spring Boot Web for REST APIs
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Eureka Client for Service Discovery
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")

    // Kotlin Support
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // Spring Boot Starter Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
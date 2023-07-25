import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    application
    id("org.springframework.boot") version "2.7.0"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("io.gitlab.arturbosch.detekt").version("1.19.0")
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"
}

group = "com.vexillum"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Spring
    api("org.springframework.boot:spring-boot-starter-data-jpa")
    api("org.springframework.boot:spring-boot-starter-web")
    api("org.springframework.boot:spring-boot-starter-webflux")
    api("org.springframework.boot:spring-boot-starter-validation")
    // Reactor
    api("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    api("io.projectreactor.kotlin:reactor-kotlin-extensions")
    //  Security
    api("org.springframework.boot:spring-boot-starter-security")
    // Jackson
    api("com.fasterxml.jackson.module:jackson-module-kotlin")

    //  JWT
    api("io.jsonwebtoken:jjwt:0.9.1")

    // System
    api("org.jetbrains.kotlin:kotlin-reflect")
    api("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // PostgresSQL
    api("org.postgresql:postgresql")

    // Detekt
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.18.0-RC2")

    // Testing
    testApi("org.mockito.kotlin:mockito-kotlin:4.1.0")
    testApi("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testApi("org.junit.jupiter:junit-jupiter-engine")
    testApi("org.hamcrest:hamcrest:2.2")
}

tasks.test {
    useJUnitPlatform()
}

tasks.getByName<BootJar>("bootJar") {
    archiveFileName.set("app.jar")
}
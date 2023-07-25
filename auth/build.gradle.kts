import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.run.BootRun
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    application
    id("org.springframework.boot") version "2.7.0"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
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
    implementation(project(":common"))
    runtimeOnly("org.postgresql:postgresql")
}

tasks.getByName<BootRun>("bootRun") {
    jvmArgs = listOf("-Dfile.encoding=utf-8")
    environment("spring.profiles.active", "dev")

    environmentIfMissing("VEXILLUM_DB_URL", "jdbc:postgresql://localhost:5432/vexillum")
    environmentIfMissing("VEXILLUM_DB_USER", "root")
    environmentIfMissing("VEXILLUM_DB_PASSWORD", "secret")
}

tasks.test {
    useJUnitPlatform()
}

tasks.getByName<BootJar>("bootJar") {
    archiveFileName.set("app.jar")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

fun JavaExec.environmentIfMissing(name: String, value: Any) {
    if (name !in environment) {
        environment(name, value)
    }
}

fun Test.environmentIfMissing(name: String, value: Any) {
    if (name !in environment) {
        environment(name, value)
    }
}
plugins {
    kotlin("jvm") version "1.8.0"
}

group = "de.iits-consulting"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0-RC")

    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.0.1")
    testImplementation("org.assertj:assertj-core:3.6.1")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.0-RC")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.1.1")
}

tasks.test {
    useJUnitPlatform()
}
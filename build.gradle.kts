plugins {
    kotlin("jvm") version "2.3.0-RC"
    id("org.jlleitschuh.gradle.ktlint") version "14.0.1"
}

group = "dev.paulshields.aoc"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.test {
    useJUnitPlatform()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("com.willowtreeapps.assertk:assertk:0.28.1")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.14.0")
}

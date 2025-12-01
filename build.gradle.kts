plugins {
    kotlin("jvm") version "2.2.20"
}

group = "dev.paulshields.adventofcode"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.test {
    useJUnitPlatform()
}

dependencies {
    testImplementation(kotlin("test"))
}

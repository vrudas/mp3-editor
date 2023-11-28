plugins {
    kotlin("jvm") version "1.9.20"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "io.mp3editor"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("org:jaudiotagger:2.0.3")
    implementation("ch.qos.logback:logback-classic:1.2.10")

    testImplementation("org.testng:testng:7.8.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

kotlin {
    jvmToolchain(21)
}

tasks.shadowJar {
    manifest {
        attributes["Main-Class"] = "io.mp3editor.MP3Editor"
    }

    archiveClassifier = "app"
}

tasks.wrapper {
    gradleVersion = "8.5-rc-4"
    distributionType = Wrapper.DistributionType.ALL
}

plugins {
    kotlin("jvm") version "1.6.10"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "mp3-file-editor"
version = "1.0.1-SNAPSHOT"

java.sourceCompatibility = JavaVersion.VERSION_1_8
java.targetCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("org:jaudiotagger:2.0.3")
    implementation("ch.qos.logback:logback-classic:1.2.10")

    testImplementation("org.testng:testng:7.5")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.shadowJar {
    manifest {
        attributes["Main-Class"] = "io.mp3editor.MP3Editor"
    }

    archiveClassifier = "app"
}

tasks.wrapper {
    gradleVersion = "8.4"
    distributionType = Wrapper.DistributionType.ALL
}

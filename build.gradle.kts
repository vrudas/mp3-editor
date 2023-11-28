plugins {
    kotlin("jvm")
    id("com.github.johnrengelman.shadow")
}

group = "io.mp3editor"
version = "1.0.0-SNAPSHOT"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("org:jaudiotagger:2.0.3")
    implementation("ch.qos.logback:logback-classic:1.2.10")

    testImplementation("org.testng:testng:7.5")
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

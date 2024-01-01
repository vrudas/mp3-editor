plugins {
    kotlin("jvm")
    id("com.github.johnrengelman.shadow")
    id("org.sonarqube")
}

group = "io.mp3editor"
version = "1.0.0-SNAPSHOT"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("org:jaudiotagger:2.0.3")
    implementation("ch.qos.logback:logback-classic:1.4.14")

    testImplementation(platform("org.junit:junit-bom:5.10.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}

tasks.shadowJar {
    manifest {
        attributes["Main-Class"] = "io.mp3editor.MP3EditorKt"
    }

    archiveClassifier = "app"
}

tasks.wrapper {
    gradleVersion = "8.6-rc-1"
    distributionType = Wrapper.DistributionType.ALL
}

sonar {
    properties {
        property("sonar.projectKey", "vrudas_mp3-editor")
        property("sonar.organization", "vrudas")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}

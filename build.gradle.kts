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
    gradleVersion = "8.5-rc-4"
    distributionType = Wrapper.DistributionType.ALL
}

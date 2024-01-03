pluginManagement {
    repositories {
        gradlePluginPortal()
    }

    plugins {
        kotlin("jvm") version "1.9.20"
        id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
        id("com.github.johnrengelman.shadow") version "8.1.1"
        id("org.sonarqube") version "4.4.1.3373"
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }

    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS

    versionCatalogs {
        create("libs") {
            version("junit", "5.10.1")
            version("logback", "1.4.14")
            version("jaudiotagger", "2.0.3")
        }
    }
}

rootProject.name = "mp3-editor"

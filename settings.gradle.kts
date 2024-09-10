pluginManagement {
    repositories {
        gradlePluginPortal()
    }

    plugins {
        kotlin("jvm") version "2.0.20"
        id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
        id("com.github.johnrengelman.shadow") version "8.1.1"
        id("org.sonarqube") version "5.1.0.4882"
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

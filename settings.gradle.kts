pluginManagement {
    repositories {
        maven {
            name = "Fabric"
            url = uri("https://maven.fabricmc.net/")
        }
        mavenCentral()
        gradlePluginPortal()
    }

    val kotlinVersion: String by settings
    val fabricLoomVersion: String by settings

    plugins {
        kotlin("jvm") version kotlinVersion
        id("fabric-loom") version fabricLoomVersion
    }
}
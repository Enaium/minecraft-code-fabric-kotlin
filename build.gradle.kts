plugins {
    kotlin("jvm")
    id("fabric-loom")
}

val modVersion: String by project
val mavenGroup: String by project

group = mavenGroup
version = modVersion

val modArchivesBaseName: String by project

base {
    archivesName = modArchivesBaseName
}

repositories {
    mavenCentral()
}

loom {
    splitEnvironmentSourceSets()

    mods {
        create("modid") {
            sourceSet("main")
            sourceSet("client")
        }
    }
}

val minecraftVersion: String by project
val yarnMappings: String by project
val loaderVersion: String by project
val fabricVersion: String by project

dependencies {
    minecraft("com.mojang:minecraft:${minecraftVersion}")
    mappings("net.fabricmc:yarn:${yarnMappings}:v2")
    modImplementation("net.fabricmc:fabric-loader:${loaderVersion}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${fabricVersion}")
}

tasks.processResources {
    inputs.property("version", project.version)

    filesMatching("fabric.mod.json") {
        expand(mapOf("version" to project.version))
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.jar {
    from("LICENSE") {
        rename {
            "${it}_${project.base.archivesName}"
        }
    }
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
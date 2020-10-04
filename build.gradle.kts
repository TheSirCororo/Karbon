plugins {
    kotlin("jvm")
    `maven-publish`
}

allprojects {
    apply(plugin = "kotlin")
    apply(plugin = "maven-publish")

    group = "com.karbonpowered"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenLocal()
        jcenter()
        maven("https://gitlab.com/XjCyan1de/maven-repo/-/raw/master/")
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
        maven("https://jitpack.io/")
    }

    dependencies {
        val coroutinesVersion: String by project
        val nettyVersion: String by project
        val gsonVersion: String by project
        val guavaVersion: String by project

        api(kotlin("stdlib-jdk8"))
        api("org.jetbrains.kotlinx", "kotlinx-coroutines-jdk8", coroutinesVersion)

        api("io.netty", "netty-all", nettyVersion)
        api("com.google.code.gson", "gson", gsonVersion)
        implementation("com.google.guava", "guava", guavaVersion)
    }

    buildDir = File("$rootDir/build/projects/${project.name}")
    val outDir = File("$rootDir/build/artifacts")

    tasks {
        jar {
            destinationDirectory.set(outDir)
            finalizedBy("publishToMavenLocal")
        }
        compileKotlin {
            kotlinOptions.jvmTarget = "1.8"
            kotlinOptions.freeCompilerArgs = listOf(
                    "-Xopt-in=kotlin.RequiresOptIn",
                    "-Xjvm-default=all"
            )
        }
    }

    publishing {
        publications {
            create<MavenPublication>("maven") {
                groupId = this@allprojects.group.toString()
                artifactId = this@allprojects.name
                version = this@allprojects.version.toString()

                from(components["java"])
            }
        }

        repositories {
            maven(url = "https://maven.pkg.github.com/KarbonPowered/Karbon") {
                credentials {
                    username = System.getenv("GITHUB_ACTOR")
                    password = System.getenv("GITHUB_TOKEN")
                }
            }
        }
    }
}

val karbonPoweredCommons = project(absoluteProjectPath("commons"))
val karbonPoweredMath = project(absoluteProjectPath("math"))
val karbonPoweredNetwork = project(absoluteProjectPath("network"))
val karbonPoweredTranslation = project(absoluteProjectPath("translation"))
val karbonPoweredText = project(absoluteProjectPath("text"))
val karbonPoweredApi = project(absoluteProjectPath("KarbonPowered-API"))
val karbonPoweredNbt = project(absoluteProjectPath("nbt"))
val karbonPoweredProtocol = project(absoluteProjectPath("protocol:protocol-java"))
val karbonPoweredAdapter = project(absoluteProjectPath("adapter"))
val karbonPoweredData = project(absoluteProjectPath("data"))
val karbonPoweredCatalog = project(absoluteProjectPath("catalog"))

dependencies {
    subprojects {
        api(karbonPoweredCommons)
        api(karbonPoweredMath)
        api(karbonPoweredNetwork)
        api(karbonPoweredTranslation)
        api(karbonPoweredText)
        api(karbonPoweredApi)
        api(karbonPoweredNbt)
        api(karbonPoweredProtocol)
        api(karbonPoweredData)
        api(karbonPoweredCatalog)
    }
}

karbonPoweredApi.dependencies {
    api(karbonPoweredCommons)
    api(karbonPoweredMath)
    api(karbonPoweredNetwork)
    api(karbonPoweredTranslation.project("translation-api"))
    api(karbonPoweredText.project("text-api"))
}

karbonPoweredNbt.dependencies {
    api(karbonPoweredApi)
}

karbonPoweredProtocol.allprojects {
    dependencies {
        api(karbonPoweredApi)
        api(karbonPoweredNbt)
    }
}

karbonPoweredAdapter.project("adapter-bukkit") {
    dependencies {
        api(karbonPoweredApi)
        api(karbonPoweredText.project("text-serializer-bungee"))
    }
}
karbonPoweredAdapter.project("adapter-bungee") {
    dependencies {
        api(karbonPoweredApi)
        api(karbonPoweredText.project("text-serializer-bungee"))
    }
}
karbonPoweredAdapter.project("adapter-velocity") {
    dependencies {
        api(karbonPoweredApi)
        api(karbonPoweredText.project("text-serializer-adventure"))
    }
}

karbonPoweredText.project("text-renderer-translatable") {
    dependencies {
        api(karbonPoweredTranslation.project("translation-api"))
        testImplementation(karbonPoweredTranslation)
    }
}

// add KarbonPowered-Commons to all
subprojects {
    if (this != karbonPoweredCommons) {
        dependencies {
            api(karbonPoweredCommons)
        }
    }
}

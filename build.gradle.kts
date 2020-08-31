plugins {
    kotlin("jvm")
    `maven-publish`
}

allprojects {
    apply(plugin = "kotlin")
    apply(plugin = "maven-publish")

    group = "com.github.karbonpowered"
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

        api(kotlin("stdlib-jdk8"))
        api("org.jetbrains.kotlinx", "kotlinx-coroutines-jdk8", coroutinesVersion)

        api("io.netty", "netty-all", nettyVersion)
        api("com.google.code.gson", "gson", gsonVersion)

        subprojects {
            api(this)
        }
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
            maven("https://gitlab.com/api/v4/projects/${System.getenv("CI_PROJECT_ID")}/packages/maven") {
                credentials(HttpHeaderCredentials::class) {
                    name = "Job-Token"
                    value = System.getenv("CI_JOB_TOKEN")
                }
                authentication {
                    create<HttpHeaderAuthentication>("header")
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
val karbonPoweredProtocol = project(absoluteProjectPath("protocol"))
val karbonPoweredAdapter = project(absoluteProjectPath("adapter"))

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

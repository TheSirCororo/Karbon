import kotlin.io.*
import java.net.*
import java.util.zip.*

plugins {
    kotlin("jvm")
    `maven-publish`
}

project.afterEvaluate {
    val m2Dir = File(System.getProperty("user.home"), ".m2")
    val repositoryDir = File(m2Dir, "repository")
    val paperDir = File(repositoryDir, "com"+File.separatorChar+"destroystokyo"+File.separatorChar+"paper"+File.separatorChar+"paper")

    fun runProcess(dir: File?, command: String): Int {
        val process = ProcessBuilder(command.split(" ")).directory(dir).start()
        process.inputStream.bufferedReader().forEachLine {
            println(it)
        }
        return process.waitFor()
    }

    if (!paperDir.exists()) {
        println("Cloning https://github.com/PaperMC/Paper.git")
        runProcess(null, "git clone --recursive https://github.com/PaperMC/Paper.git")
        runProcess(File( "Paper"), "sh paper install")
    }
}

allprojects {
    apply(plugin = "kotlin")
    apply(plugin = "maven-publish")

    group = "com.karbonpowered"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenLocal()
        jcenter()
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
        maven("https://jitpack.io/")
    }

    dependencies {
        val coroutinesVersion: String by project

        api(kotlin("stdlib-jdk8"))
        api("org.jetbrains.kotlinx", "kotlinx-coroutines-jdk8", coroutinesVersion)

        testApi("org.junit.jupiter", "junit-jupiter", "5.7.0")
    }

    tasks {
        jar {
            finalizedBy("publishToMavenLocal")
        }
        val kotlinOptions: org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions.() -> Unit = {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf(
                "-Xjvm-default=all",
                "-Xopt-in=kotlin.RequiresOptIn"
            )
        }
        compileKotlin {
            kotlinOptions(kotlinOptions)
        }
        compileTestKotlin {
            kotlinOptions(kotlinOptions)
        }
        test {
            useJUnitPlatform()
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
            if (System.getenv("GITHUB_TOKEN") != null) {
                maven(url = "https://maven.pkg.github.com/KarbonPowered/Karbon") {
                    credentials {
                        username = System.getenv("GITHUB_ACTOR")
                        password = System.getenv("GITHUB_TOKEN")
                    }
                }
            }

            if (System.getenv("CI_JOB_TOKEN") != null) {
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
}

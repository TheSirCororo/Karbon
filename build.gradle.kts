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

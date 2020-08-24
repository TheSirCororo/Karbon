plugins {
    kotlin("jvm")
    maven
}

allprojects {
    repositories {
        jcenter()
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
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

    tasks {
        compileKotlin {
            kotlinOptions.jvmTarget = "14"
            kotlinOptions.freeCompilerArgs = listOf(
                    "-Xopt-in=kotlin.RequiresOptIn",
                    "-Xjvm-default=all"
            )
        }
    }
}
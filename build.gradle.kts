plugins {
    kotlin("jvm")
    maven
}

allprojects {
    group = "com.github.karbonpowered"
    version = "0.0.1"

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

val karbonPoweredCommons = project(absoluteProjectPath("KarbonPowered-Commons"))
val karbonPoweredMath = project(absoluteProjectPath("KarbonPowered-Math"))
val karbonPoweredNetwork = project(absoluteProjectPath("KarbonPowered-Network"))
val karbonPoweredTranslation = project(absoluteProjectPath("KarbonPowered-Translation"))
val karbonPoweredText = project(absoluteProjectPath("KarbonPowered-Text"))
val karbonPoweredApi = project(absoluteProjectPath("KarbonPowered-API"))
val karbonPoweredNbt = project(absoluteProjectPath("KarbonPowered-NBT"))
val karbonPoweredProtocol = project(absoluteProjectPath("KarbonPowered-Protocol"))
val karbonPoweredAdapter = project(absoluteProjectPath("KarbonPowered-Adapter"))

karbonPoweredApi.dependencies {
    api(karbonPoweredCommons)
    api(karbonPoweredMath)
    api(karbonPoweredNetwork)
    api(karbonPoweredTranslation)
    api(karbonPoweredText)
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

karbonPoweredAdapter.allprojects {
    dependencies {
        api(karbonPoweredApi)
    }
}

project(karbonPoweredText.absoluteProjectPath("text-renderer-translatable")) {
    dependencies {
        api(karbonPoweredTranslation)
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

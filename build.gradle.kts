plugins {
    kotlin("jvm")
    maven
}

repositories {
    jcenter()
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    maven("https://jitpack.io/")
}

dependencies {
    val coroutinesVersion: String by project
    val netty_version: String by project
    val gsonVersion: String by project
    val commons_version: String by project
    val math_version: String by project
    val network_version: String by project
    val protocol_version: String by project
    val api_version: String by project

    api(kotlin("stdlib-jdk8"))
    api("org.jetbrains.kotlinx", "kotlinx-coroutines-jdk8", coroutinesVersion)

    api("io.netty", "netty-all", netty_version)
    api("com.google.code.gson", "gson", gsonVersion)

    api("com.github.karbonpowered", "commons", commons_version)
    api("com.github.karbonpowered", "math", math_version)
    api("com.github.karbonpowered", "network", network_version)
    api("com.github.karbonpowered", "protocol", protocol_version)
    api("com.github.karbonpowered", "karbonpowered-api", api_version)
    api("com.github.Steveice10:MCProtocolLib:3e33a3c81e")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "14"
    }
}
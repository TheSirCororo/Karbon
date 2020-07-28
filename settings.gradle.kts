pluginManagement {
    val kotlin_version: String by settings

    plugins {
        kotlin("jvm") version kotlin_version
    }

    repositories {
        jcenter()
        gradlePluginPortal()
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
    }
}
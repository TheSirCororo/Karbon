dependencies {
    val nettyVersion: String by project
    val gsonVersion: String by project

    api(project(relativeProjectPath(":karbonpowered-commons")))
    api("io.netty", "netty-all", nettyVersion)
    api("com.google.code.gson", "gson", gsonVersion)
}
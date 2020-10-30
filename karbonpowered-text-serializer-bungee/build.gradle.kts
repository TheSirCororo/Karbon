dependencies {
    api(project(relativeProjectPath(":karbonpowered-text-api")))
    compileOnly("net.md-5", "bungeecord-chat", "1.16-R0.3")

    testImplementation(project(relativeProjectPath(":karbonpowered-text-impl")))
    testImplementation("net.md-5", "bungeecord-chat", "1.16-R0.3")
}
dependencies {
    api(project(relativeProjectPath(":karbonpowered-text-api")))
    api(project(relativeProjectPath(":karbonpowered-text-serializer-adventure")))
    compileOnly("net.kyori", "adventure-api", "4.1.1")
    compileOnly("net.kyori", "adventure-text-serializer-legacy", "4.1.1")
}
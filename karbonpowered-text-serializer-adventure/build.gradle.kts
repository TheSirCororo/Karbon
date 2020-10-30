dependencies {
    api(project(relativeProjectPath(":karbonpowered-text-api")))
    compileOnly("net.kyori", "adventure-api", "4.1.1")
    compileOnly("net.kyori", "adventure-text-serializer-legacy", "4.1.1")
    testApi("net.kyori", "adventure-api", "4.1.1")
    testApi("org.junit.jupiter", "junit-jupiter-api", "5.7.0")
}
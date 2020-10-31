repositories {
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    api(project(relativeProjectPath(":karbonpowered-game-api")))
    api(project(relativeProjectPath(":karbonpowered-item-impl")))
    api(project(relativeProjectPath(":karbonpowered-data-impl")))
    api(project(relativeProjectPath(":karbonpowered-constant-api")))
    api(project(relativeProjectPath(":karbonpowered-text-serializer-bungee")))
    compileOnlyApi("com.destroystokyo.paper", "paper-api", "1.16.3-R0.1-SNAPSHOT")
    compileOnlyApi("com.destroystokyo.paper", "paper", "1.16.3-R0.1-SNAPSHOT")
}
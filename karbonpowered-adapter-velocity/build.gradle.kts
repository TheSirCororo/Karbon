repositories {
    maven("https://repo.velocitypowered.com/snapshots/")
}

dependencies {
    api(project(relativeProjectPath(":karbonpowered-game-api")))
    api(project(relativeProjectPath(":karbonpowered-text-serializer-adventure")))
    compileOnlyApi("com.velocitypowered", "velocity-api", "1.1.0-SNAPSHOT")
}
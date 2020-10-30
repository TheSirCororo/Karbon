repositories {
    maven("https://repo.velocitypowered.com/snapshots/")
}

dependencies {
    api(project(relativeProjectPath(":karbonpowered-api")))
    api(project(relativeProjectPath(":karbonpowered-text-serializer-adventure")))
    compileOnly("com.velocitypowered", "velocity-api", "1.1.0-SNAPSHOT")
}
dependencies {
    val gsonVersion: String by project

    api(project(relativeProjectPath(":karbonpowered-text-api")))
    compileOnly("com.google.code.gson", "gson", gsonVersion)
}
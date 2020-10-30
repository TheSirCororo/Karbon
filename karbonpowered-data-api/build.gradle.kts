dependencies {
    val gsonVersion: String by project
    val guavaVersion: String by project

    api(project(relativeProjectPath(":karbonpowered-commons")))
    api(project(relativeProjectPath(":karbonpowered-catalog-api")))
    api("com.google.code.gson", "gson", gsonVersion)
    api("com.google.guava", "guava", guavaVersion)
}
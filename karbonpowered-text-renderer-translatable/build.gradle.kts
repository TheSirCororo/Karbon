dependencies {
    api(project(relativeProjectPath(":karbonpowered-text-impl")))
    api(project(relativeProjectPath(":karbonpowered-translation-api")))

    testApi(project(relativeProjectPath(":karbonpowered-text-impl")))
    testApi(project(relativeProjectPath(":karbonpowered-translation-impl")))
}
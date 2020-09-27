dependencies {
    val guavaVersion: String by project

    api(project(":catalog:catalog-api"))
    implementation("com.google.guava", "guava", guavaVersion)
}
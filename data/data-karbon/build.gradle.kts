dependencies {
    val guavaVersion: String by project

    api(project(":data:data-api"))
    implementation("com.google.guava", "guava", guavaVersion)
}
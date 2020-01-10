val ktorVersion: String by project
val koinVersion: String by project

dependencies {
    implementation(project(":broker-storage"))
    implementation(project(":broker-server"))
    implementation("io.ktor:ktor-freemarker:$ktorVersion")
    implementation("org.koin:koin-core:$koinVersion")
}
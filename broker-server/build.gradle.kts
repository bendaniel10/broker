val ktorVersion: String by project
val koinVersion: String by project

dependencies {
    api("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("org.koin:koin-core:$koinVersion")
}
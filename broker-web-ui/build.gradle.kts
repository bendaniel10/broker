val ktorVersion: String by project

dependencies {
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-freemarker:$ktorVersion")
}
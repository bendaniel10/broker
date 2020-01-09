plugins { application }
val ktorVersion: String by project
val koinVersion: String by project

dependencies {
    implementation(project(":broker-storage"))
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-freemarker:$ktorVersion")
    implementation("org.koin:koin-core:$koinVersion")
}

application {
    mainClassName = "com.bendaniel10.broker.web.ui.WebUIServer"
}
plugins { application }
val ktorVersion: String by project
val koinVersion: String by project

dependencies {
    implementation(project(":broker-server"))
    implementation(project(":broker-storage"))
    implementation(project(":broker-web-ui"))
    implementation("org.koin:koin-core:$koinVersion")
}

application {
    mainClassName = "com.bendaniel10.broker.app.BrokerApp"
}
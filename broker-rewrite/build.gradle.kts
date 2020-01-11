val ktorVersion: String by project
val koinVersion: String by project
val kotlinLogging: String by project
val sl4jVersion: String by project

dependencies {
    implementation(project(":broker-storage"))
    implementation(project(":broker-server"))
    implementation("org.koin:koin-core:$koinVersion")
    implementation("io.ktor:ktor-client:$ktorVersion")
    implementation("io.ktor:ktor-client-apache:$ktorVersion")
    implementation("io.github.microutils:kotlin-logging:$kotlinLogging")
    implementation("org.slf4j:slf4j-simple:$sl4jVersion")
}
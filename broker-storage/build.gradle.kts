val potassiumNitriteVersion: String by project
val kotlinLogging: String by project
val koinVersion: String by project

dependencies {
    implementation("org.dizitart:potassium-nitrite:$potassiumNitriteVersion")
    implementation("io.github.microutils:kotlin-logging:$kotlinLogging")
    implementation("org.koin:koin-core:$koinVersion")
}

val potassiumNitriteVersion: String by project
val kotlinLogging: String by project

dependencies {
    implementation("org.dizitart:potassium-nitrite:$potassiumNitriteVersion")
    implementation("io.github.microutils:kotlin-logging:$kotlinLogging")
}

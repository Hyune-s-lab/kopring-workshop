dependencies {
    implementation(project(":common"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    val coroutineVersion: String by project
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutineVersion}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-slf4j:${coroutineVersion}")

    val logbackVersion: String by project
    implementation("ch.qos.logback.contrib:logback-json-classic:${logbackVersion}")
    implementation("ch.qos.logback.contrib:logback-jackson:${logbackVersion}")

    val springdocOpenapi: String by project
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${springdocOpenapi}")

    val datadogApiVersion: String by project
    implementation("com.datadoghq:dd-trace-api:${datadogApiVersion}")
}

tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

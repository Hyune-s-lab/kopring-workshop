dependencies {
    implementation(project(":common"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    val springdocOpenapi: String by project
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${springdocOpenapi}")

    val coroutineVersion: String by project
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutineVersion}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-slf4j:${coroutineVersion}")

    val mongodbVersion: String by project
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
}

tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

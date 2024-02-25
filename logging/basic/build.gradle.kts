dependencies {
    implementation(project(":common"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    val springdocOpenapi: String by project
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${springdocOpenapi}")
}

tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {
    // kotest
    val kotestVersion: String by project
    val kotestExtVersion: String by project

    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")
    testImplementation("io.kotest:kotest-extensions-now:${kotestVersion}")
    implementation("io.kotest.extensions:kotest-extensions-spring:$kotestExtVersion")
}

package com.example.kopringworkshop.externalapi.wiremock

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WiremockApplication {
}

fun main(vararg args: String) {
    runApplication<WiremockApplication>(*args)
}

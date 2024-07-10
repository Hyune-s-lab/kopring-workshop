package com.example.kopringworkshop.externalapi.wiremock

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableWebMvc
@SpringBootApplication
class WiremockApplication {
}

fun main(vararg args: String) {
    runApplication<WiremockApplication>(*args)
}

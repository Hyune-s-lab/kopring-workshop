package com.example.kopringworkshop.datadog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableWebMvc
@SpringBootApplication
class LoggingDatadogWebApplication {
}

fun main(vararg args: String) {
    runApplication<LoggingDatadogWebApplication>(*args)
}

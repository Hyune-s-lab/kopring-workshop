package com.example.kopringworkshop.logback

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableWebMvc
@SpringBootApplication
class LoggingLogbackWebApplication {
}

fun main(vararg args: String) {
    runApplication<LoggingLogbackWebApplication>(*args)
}

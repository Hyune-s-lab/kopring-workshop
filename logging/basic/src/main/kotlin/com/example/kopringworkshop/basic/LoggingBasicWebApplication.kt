package com.example.kopringworkshop.basic

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableWebMvc
@SpringBootApplication
class LoggingBasicWebApplication {
}

fun main(vararg args: String) {
    runApplication<LoggingBasicWebApplication>(*args)
}

package com.example.kopringworkshop.basic

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LoggingBasicWebApplication {
}

fun main(vararg args: String) {
    runApplication<LoggingBasicWebApplication>(*args)
}

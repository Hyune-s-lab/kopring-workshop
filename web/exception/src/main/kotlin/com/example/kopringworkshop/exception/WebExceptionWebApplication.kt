package com.example.kopringworkshop.exception

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableWebMvc
@SpringBootApplication
class WebExceptionWebApplication {
}

fun main(vararg args: String) {
    runApplication<WebExceptionWebApplication>(*args)
}

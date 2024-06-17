package com.example.kopringworkshop.test.testcontainers

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableWebMvc
@SpringBootApplication
class TestContainersApplication {
}

fun main(vararg args: String) {
    runApplication<TestContainersApplication>(*args)
}

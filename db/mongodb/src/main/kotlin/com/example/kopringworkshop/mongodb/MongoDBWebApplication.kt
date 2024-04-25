package com.example.kopringworkshop.mongodb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableWebMvc
@SpringBootApplication
class MongoDBWebApplication {
}

fun main(vararg args: String) {
    runApplication<MongoDBWebApplication>(*args)
}

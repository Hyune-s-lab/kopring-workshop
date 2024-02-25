package com.example.kopringworkshop.logback.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class IndexController() {
    @GetMapping("/_health")
    suspend fun health(): String {
        return System.currentTimeMillis().toString()
    }
}

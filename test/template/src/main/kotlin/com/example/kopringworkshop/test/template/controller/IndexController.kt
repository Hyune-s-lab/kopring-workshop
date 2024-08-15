package com.example.kopringworkshop.test.template.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
class IndexController {
    @GetMapping("/health")
    fun health(): String {
        return Instant.now().toString()
    }
}

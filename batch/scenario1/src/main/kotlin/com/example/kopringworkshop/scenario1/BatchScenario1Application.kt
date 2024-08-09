package com.example.kopringworkshop.scenario1

import com.example.kopringworkshop.scenario1.service.TermOfServiceAgreeService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BatchScenario1Application {
}

fun main(vararg args: String) {
    val context = runApplication<BatchScenario1Application>(*args)

    context.getBean(TermOfServiceAgreeService::class.java).agree()
}

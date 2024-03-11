package com.example.kopringworkshop.exception.controller

import com.example.kopringworkshop.exception.support.exception.HandledBusinessException
import com.example.kopringworkshop.exception.support.exception.NotHandledBusinessException
import io.swagger.v3.oas.annotations.Operation
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TestUtilController {
    private val log = LoggerFactory.getLogger(this::class.java)

    @Operation(summary = "run exception")
    @PostMapping("/test-util/run-exception")
    fun runException(@RequestBody request: RunExceptionRequest) {
        when (request.exceptionName) {
            "ILLEGAL_ARGUMENT"     -> throw IllegalArgumentException(request.message)
            "ILLEGAL_STATE"        -> throw IllegalStateException(request.message)
            "HANDLED_BUSINESS"     -> throw HandledBusinessException(request.message)
            "NOT_HANDLED_BUSINESS" -> throw NotHandledBusinessException(request.message)
            else                   -> throw RuntimeException(request.message)
        }
    }

    data class RunExceptionRequest(val exceptionName: String, val message: String)
}


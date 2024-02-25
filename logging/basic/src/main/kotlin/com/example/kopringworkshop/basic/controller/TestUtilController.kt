package com.example.kopringworkshop.basic.controller

import io.swagger.v3.oas.annotations.Operation
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.slf4j.event.Level
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.CONFLICT
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class TestUtilController {
    private val log = LoggerFactory.getLogger(this::class.java)

    @Operation(summary = "run exception")
    @PostMapping("/test-util/run-exception")
    fun runException(@RequestBody request: RunExceptionRequest) {
        val message = "call run exception api: status=${request.statusCode}"

        when (request.statusCode) {
            BAD_REQUEST.value() -> throw IllegalArgumentException(message)
            CONFLICT.value()    -> throw IllegalStateException(message)
            else                -> throw RuntimeException(message)
        }
    }

    @Operation(summary = "run logging")
    @PostMapping("/test-util/run-logging")
    fun runLogging(
        @RequestParam level: Level,
        @RequestParam extra1: String = "NONE1",
        @RequestParam extra2: String = "NONE2",

        @RequestBody request: RunLoggingRequest,
    ) {
        val message = "message=${request.message}, extra1=$extra1, extra2=$extra2, mdc=${MDC.getCopyOfContextMap()}}}"

        when (level) {
            Level.INFO  -> log.info(message)
            Level.ERROR -> log.error(message)
            Level.WARN  -> log.warn(message)
            Level.DEBUG -> log.debug(message)
            Level.TRACE -> log.trace(message)
        }
    }

    data class RunExceptionRequest(val statusCode: Int, val message: String)
    data class RunLoggingRequest(val message: String)
}


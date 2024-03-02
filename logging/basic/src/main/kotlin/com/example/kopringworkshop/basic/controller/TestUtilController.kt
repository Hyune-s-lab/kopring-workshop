package com.example.kopringworkshop.basic.controller

import com.example.kopringworkshop.basic.service.AsyncRunExceptionService
import com.example.kopringworkshop.basic.service.CoroutineRunExceptionService
import io.swagger.v3.oas.annotations.Operation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.slf4j.MDCContext
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
class TestUtilController(
    private val asyncRunExceptionService: AsyncRunExceptionService,
    private val coroutineRunExceptionService: CoroutineRunExceptionService,
) {
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

    @Operation(summary = "run exception - async")
    @PostMapping("/test-util/run-exception/async")
    fun asyncRunException(@RequestBody request: RunExceptionRequest) {
        val message = "call run exception api: status=${request.statusCode}"

        log.info("### call async run exception api")
        log.info("### 요청이 들어온 스레드 mdc=${MDC.getCopyOfContextMap()}")

        asyncRunExceptionService.runException(request.statusCode, message)
    }

    @Operation(summary = "run exception - coroutine")
    @PostMapping("/test-util/run-exception/coroutine")
    fun coroutineRunException(@RequestBody request: RunExceptionRequest) {
        val message = "call run exception api: status=${request.statusCode}"

        log.info("### call coroutine run exception api")
        log.info("### 요청이 들어온 스레드 mdc=${MDC.getCopyOfContextMap()}")

        CoroutineScope(Dispatchers.Default).launch(MDCContext()) {
            coroutineRunExceptionService.runException(request.statusCode, message)
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


package com.example.kopringworkshop.datadog.service

import datadog.trace.api.CorrelationIdentifier
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class CoroutineRunExceptionService {
    private val log = LoggerFactory.getLogger(this::class.java)

    suspend fun runException(statusCode: Int, message: String) {
        log.info("### call coroutine run exception service")
        log.info("### coroutine 으로 호출된 스레드 mdc=${MDC.getCopyOfContextMap()}, traceId=${CorrelationIdentifier.getTraceId()}")

        when (statusCode) {
            HttpStatus.BAD_REQUEST.value() -> throw IllegalArgumentException(message)
            HttpStatus.CONFLICT.value()    -> throw IllegalStateException(message)
            else                           -> throw RuntimeException(message)
        }
    }
}

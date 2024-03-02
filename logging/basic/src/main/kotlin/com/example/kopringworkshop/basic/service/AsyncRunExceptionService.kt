package com.example.kopringworkshop.basic.service

import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.http.HttpStatus
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class AsyncRunExceptionService {
    private val log = LoggerFactory.getLogger(this::class.java)

    @Async
    fun runException(statusCode: Int, message: String) {
        log.info("### call async run exception service")
        log.info("### async 로 호출된 스레드 mdc=${MDC.getCopyOfContextMap()}")

        when (statusCode) {
            HttpStatus.BAD_REQUEST.value() -> throw IllegalArgumentException(message)
            HttpStatus.CONFLICT.value()    -> throw IllegalStateException(message)
            else                           -> throw RuntimeException(message)
        }
    }
}

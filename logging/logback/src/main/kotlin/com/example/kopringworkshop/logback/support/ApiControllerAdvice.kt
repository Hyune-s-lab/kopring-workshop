package com.example.kopringworkshop.logback.support

import jakarta.servlet.http.HttpServletRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApiControllerAdvice {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: IllegalArgumentException, request: HttpServletRequest): ErrorResponse {
        return ErrorResponse(code = "400", message = e.message).also {
            errorLogging(e, request)
        }
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(IllegalStateException::class)
    fun handleIllegalStateException(e: IllegalStateException, request: HttpServletRequest): ErrorResponse {
        return ErrorResponse(code = "400", message = e.message).also {
            errorLogging(e, request)
        }
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception, request: HttpServletRequest): ErrorResponse {
        return ErrorResponse(code = "500", message = e.message).also {
            errorLogging(e, request)
        }
    }

    /**
     * 표준 예외 응답과 별개로 also 절을 통한 호출로 로깅 처리
     */
    private fun errorLogging(e: Exception, request: HttpServletRequest) {
        val httpRequestMessage = request.run {
            "uri=${requestURI}, " +
                "headers=${headerNames.toList().associateWith { getHeader(it) }}, " +
                "params=${parameterMap.mapValues { it.value.toList() }}, " +
                "body=${reader.readText()}"
        }
        log.error(httpRequestMessage)
        log.error("handle ${e.javaClass.simpleName}", e)
    }
}

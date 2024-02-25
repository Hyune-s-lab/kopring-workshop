package com.example.kopringworkshop.logback.support

import jakarta.servlet.http.HttpServletRequest
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.MDC
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
            errorLogging(e, request.toMDCDto())
        }
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(IllegalStateException::class)
    fun handleIllegalStateException(e: IllegalStateException, request: HttpServletRequest): ErrorResponse {
        return ErrorResponse(code = "400", message = e.message).also {
            errorLogging(e, request.toMDCDto())
        }
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception, request: HttpServletRequest): ErrorResponse {
        return ErrorResponse(code = "500", message = e.message).also {
            errorLogging(e, request.toMDCDto())
        }
    }

    private fun errorLogging(e: Exception, request: HttpRequestForMDC) {
        MDC.put("request", Json.encodeToString(request))
        log.error("handle ${e.javaClass.simpleName}", e)
    }

    private fun HttpServletRequest.toMDCDto(): HttpRequestForMDC {
        return HttpRequestForMDC(
            uri = this.requestURI,
            headers = this.headerNames.toList().associateWith { this.getHeader(it) },
            params = this.parameterMap.mapValues { it.value.toList() },
            body = this.inputStream.bufferedReader().use { it.readText() }
        )
    }

    @Serializable
    private data class HttpRequestForMDC(
        val uri: String,
        val headers: Map<String, String>,
        val params: Map<String, List<String>>,
        val body: String,
    )
}

package com.example.kopringworkshop.exception.support

import com.example.kopringworkshop.exception.support.exception.BaseException
import com.example.kopringworkshop.exception.support.exception.HandledBusinessException
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.CONFLICT
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.ZonedDateTime
import java.util.*

@RestControllerAdvice
class ApiControllerAdvice {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: IllegalArgumentException): ErrorResponse {
        return ErrorResponse(code = e.javaClass.simpleName, message = e.message).also {
            errorLogging(e)
        }
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(IllegalStateException::class)
    fun handleIllegalStateException(e: IllegalStateException): ErrorResponse {
        return ErrorResponse(code = e.javaClass.simpleName, message = e.message).also {
            errorLogging(e)
        }
    }

    @ResponseStatus(CONFLICT)
    @ExceptionHandler(HandledBusinessException::class)
    fun handleHandledBusinessException(e: HandledBusinessException): ErrorResponse {
        return ErrorResponse(code = e.javaClass.simpleName, message = e.message).also {
            errorLogging(e)
        }
    }

    @ResponseStatus(CONFLICT)
    @ExceptionHandler(BaseException::class)
    fun handleBaseException(e: BaseException): ErrorResponse {
        return ErrorResponse(code = e.javaClass.simpleName, message = "${e.message}, handled: BaseException").also {
            errorLogging(e)
        }
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ErrorResponse {
        // also 의 정석적인 it 사용
        return e.also { errorLogging(it) }
            .toResponse()

        // 가독성을 위해 also 를 뒤에 위치
        //        return e.toResponse().also {
        //            errorLogging(e)
        //        }
    }

    /**
     * 표준 예외 응답과 별개로 also 절을 통한 호출로 로깅 처리
     */
    private fun errorLogging(e: Exception) {
        log.error("handle ${e.javaClass.simpleName}", e)
    }

    /**
     * 확장 함수를 통해 간결화할 수도 있습니다.
     */
    private fun Exception.toResponse(): ErrorResponse {
        return ErrorResponse(code = this.javaClass.simpleName, message = this.message)
    }

    /**
     * 표준 예외 응답 객체
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    data class ErrorResponse(
        val code: String,
        val message: String?,
        val data: Any? = null,
    ) {
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        val timestamp: ZonedDateTime = ZonedDateTime.now()
        val traceId: String = UUID.randomUUID().toString()
    }
}

package com.example.kopringworkshop.datadog.support

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import datadog.trace.api.CorrelationIdentifier
import java.time.ZonedDateTime

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
    val traceId: String = CorrelationIdentifier.getTraceId() ?: "no-traceId"
}

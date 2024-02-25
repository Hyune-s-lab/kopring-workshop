package com.example.kopringworkshop.basic.config

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.time.Duration
import java.time.LocalDateTime
import java.util.*

@Component
class CustomRequestFilter: OncePerRequestFilter() {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    override fun doFilterInternal(
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        // 모든 request 에 대해 MDC 에 traceId 를 설정
        MDC.put("traceId", UUID.randomUUID().toString())

        // requestedAt 기록
        httpServletRequest.setAttribute("requestAt", LocalDateTime.now())

        // 모든 request 에 대해 request body 를 caching
        filterChain.doFilter(CachedBodyHttpServletRequest(httpServletRequest), httpServletResponse)

        // measure response duration
        (httpServletRequest.getAttribute("requestAt") as LocalDateTime to LocalDateTime.now()).also { (requestAt, responseAt) ->
            val duration = Duration.between(requestAt, responseAt).toMillis()
            log.info("### duration=$duration ms, requestAt=$requestAt, responseAt=$responseAt")
        }
    }
}

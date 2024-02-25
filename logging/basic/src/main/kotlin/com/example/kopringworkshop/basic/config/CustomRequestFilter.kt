package com.example.kopringworkshop.basic.config

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.MDC
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.*

@Component
class CustomRequestFilter: OncePerRequestFilter() {
    override fun doFilterInternal(
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        // 모든 request 에 대해 MDC 에 traceId 를 설정
        MDC.put("traceId", UUID.randomUUID().toString())

        // 모든 request 에 대해 request body 를 caching
        filterChain.doFilter(CachedBodyHttpServletRequest(httpServletRequest), httpServletResponse)
    }
}

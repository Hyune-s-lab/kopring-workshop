package com.example.kopringworkshop.datadog.config

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class CustomRequestFilter: OncePerRequestFilter() {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    override fun doFilterInternal(
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        // 모든 request 에 대해 request body 를 caching
        filterChain.doFilter(CachedBodyHttpServletRequest(httpServletRequest), httpServletResponse)
    }
}

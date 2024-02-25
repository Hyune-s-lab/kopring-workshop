package com.example.kopringworkshop.basic.config

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class CachingHttpServletRequestBodyFilter: OncePerRequestFilter() {
    override fun doFilterInternal(
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        // 모든 request 에 대해 request body 를 caching
        filterChain.doFilter(CachedBodyHttpServletRequest(httpServletRequest), httpServletResponse)
    }
}

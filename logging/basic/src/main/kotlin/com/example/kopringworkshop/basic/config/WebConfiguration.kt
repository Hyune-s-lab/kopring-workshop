package com.example.kopringworkshop.basic.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfiguration: WebMvcConfigurer {
    /**
     * `feature 3. handle request body` 에서 disable
     */
    //    override fun addInterceptors(registry: InterceptorRegistry) {
    //        registry
    //            .addInterceptor(LoggingInterceptor())
    //            .addPathPatterns("/**")
    //    }
}

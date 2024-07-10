package com.example.kopringworkshop.externalapi.wiremock.config

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@Configuration
@EnableFeignClients(basePackages = ["com.example.kopringworkshop.externalapi.wiremock"])
class ApplicationConfig {
}

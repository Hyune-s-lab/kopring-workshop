package com.example.kopringworkshop.basic.config.async

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.TaskExecutor
import org.springframework.scheduling.annotation.AsyncConfigurer
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

@Configuration
class AsyncConfiguration: AsyncConfigurer {
    @Bean
    fun taskExecutor(): TaskExecutor {
        return ThreadPoolTaskExecutor().apply {
            setTaskDecorator(CustomTaskDecorator())
        }
    }
}

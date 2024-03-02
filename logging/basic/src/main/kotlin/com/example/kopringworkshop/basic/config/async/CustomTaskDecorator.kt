package com.example.kopringworkshop.basic.config.async

import org.slf4j.MDC
import org.springframework.core.task.TaskDecorator

class CustomTaskDecorator: TaskDecorator {
    override fun decorate(runnable: Runnable): Runnable {
        val originContextMap = MDC.getCopyOfContextMap()

        return Runnable {
            originContextMap?.let { MDC.setContextMap(it) }
            runnable.run()
        }
    }
}

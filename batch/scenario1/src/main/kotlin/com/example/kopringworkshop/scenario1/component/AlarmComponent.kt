package com.example.kopringworkshop.scenario1.component

import com.example.common.KLogging
import org.springframework.stereotype.Component

@Component
class AlarmComponent {
    /**
     * email 발송을 mocking
     */
    fun sendEmail(email: String) {
        Thread.sleep(50)
    }

    companion object : KLogging()
}

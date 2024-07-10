package com.example.kopringworkshop.externalapi.wiremock

import com.example.common.KLogging
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles(value = ["test"])
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class AbstractWiremockApplication {
    companion object : KLogging() {
    }
}

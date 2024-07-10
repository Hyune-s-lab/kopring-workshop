package com.example.kopringworkshop.externalapi.wiremock

import com.example.common.KLogging
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles(value = ["test"])
@AutoConfigureWireMock(port = 0, stubs = ["classpath:/mappings"])
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class AbstractWiremockApplication {
    companion object : KLogging() {
    }
}

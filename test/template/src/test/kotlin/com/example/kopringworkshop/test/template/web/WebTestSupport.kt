package com.example.kopringworkshop.test.template.web

import com.example.common.KLogging
import net.datafaker.Faker
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.TestConstructor.AutowireMode

@ActiveProfiles(value = ["test"])
@TestConstructor(autowireMode = AutowireMode.ALL)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
abstract class WebTestSupport {
    companion object : KLogging() {
        @JvmStatic
        protected val faker = Faker()
    }
}

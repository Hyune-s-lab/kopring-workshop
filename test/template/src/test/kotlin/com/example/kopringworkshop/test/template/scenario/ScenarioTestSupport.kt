package com.example.kopringworkshop.test.template.scenario

import com.example.common.KLogging
import net.datafaker.Faker
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles(value = ["test"])
@TestMethodOrder(MethodOrderer.DisplayName::class)
@TestInstance(Lifecycle.PER_CLASS)
abstract class ScenarioTestSupport {
    companion object : KLogging() {
        @JvmStatic
        protected val faker = Faker()
    }
}

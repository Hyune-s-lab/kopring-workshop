package com.example.kopringworkshop.mysqldb

import com.example.common.KLogging
import net.datafaker.Faker
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles(value = ["test"])
@Import(TestContainerConfig::class)
@SpringBootTest
abstract class TestDefaultSupport {
    companion object : KLogging() {
        @JvmStatic
        protected val faker = Faker()
    }
}

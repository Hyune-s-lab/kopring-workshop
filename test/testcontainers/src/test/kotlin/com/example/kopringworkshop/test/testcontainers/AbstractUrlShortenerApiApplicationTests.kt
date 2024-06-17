package com.example.kopringworkshop.test.testcontainers

import com.example.common.KLogging
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.containers.MySQLContainer

@ActiveProfiles(value = ["test"])
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class AbstractUrlShortenerApiApplicationTests {

    companion object: KLogging() {

        @JvmField
        val mysqlContainer = MySQLContainer("mysql:8.0.33").apply {
            withDatabaseName("person")
            withUsername("root")
            withPassword("u1234")
        }

        init {
            mysqlContainer.start()
            System.setProperty("spring.datasource.url", mysqlContainer.jdbcUrl)
            System.setProperty("spring.datasource.username", mysqlContainer.username)
            System.setProperty("spring.datasource.password", mysqlContainer.password)
        }
    }
}

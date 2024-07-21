package com.example.kopringworkshop.mysqldb

import com.example.common.KLogging
import org.springframework.boot.test.context.TestConfiguration
import org.testcontainers.containers.MySQLContainer

@TestConfiguration
class TestContainerConfig {

    companion object : KLogging() {

        @JvmField
        val mysqlContainer = MySQLContainer("mysql:8.0.33").apply {
            withDatabaseName("test_schema")
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

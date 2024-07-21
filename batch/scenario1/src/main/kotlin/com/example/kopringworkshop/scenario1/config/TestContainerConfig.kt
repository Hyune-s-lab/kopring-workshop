package com.example.kopringworkshop.scenario1.config

import com.example.common.KLogging
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.core.annotation.Order
import org.testcontainers.containers.MySQLContainer

@Profile("local")
@Configuration
@Order(0)
class TestContainerConfig {

    companion object : KLogging() {
        private const val MYSQL_PORT = "23306"

        @JvmField
        val mysqlContainer = MySQLContainer("mysql:8.0.33").apply {
            withDatabaseName("batch_scenario1")
            withUsername("root")
            withPassword("u1234")
            setPortBindings(listOf("$MYSQL_PORT:3306"))
        }

        init {
            mysqlContainer.start()
            System.setProperty("spring.datasource.url", mysqlContainer.jdbcUrl)
            System.setProperty("spring.datasource.username", mysqlContainer.username)
            System.setProperty("spring.datasource.password", mysqlContainer.password)
        }
    }
}

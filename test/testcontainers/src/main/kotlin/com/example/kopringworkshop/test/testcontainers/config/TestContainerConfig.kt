package com.example.kopringworkshop.test.testcontainers.config

import com.example.common.KLogging
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.core.annotation.Order
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.MySQLContainer

@Profile("local")
@Configuration
@Order(0)
class TestContainerConfig {

    companion object: KLogging() {
        private const val MYSQL_PORT = "23306"
        private const val REDIS_PORT = "26379"

        @JvmField
        val mysqlContainer = MySQLContainer("mysql:8.0.33").apply {
            withDatabaseName("person")
            withUsername("root")
            withPassword("u1234")
            setPortBindings(listOf("$MYSQL_PORT:3306"))
        }

        @JvmField
        val redisContainer = GenericContainer("redis:6.2.6").apply {
            exposedPorts = listOf(6379)
            setPortBindings(listOf("$REDIS_PORT:6379"))
        }

        init {
            mysqlContainer.start()
            System.setProperty("spring.datasource.url", mysqlContainer.jdbcUrl)
            System.setProperty("spring.datasource.username", mysqlContainer.username)
            System.setProperty("spring.datasource.password", mysqlContainer.password)

            redisContainer.start()
            System.setProperty("spring.redis.host", redisContainer.host)
            System.setProperty("spring.redis.port", REDIS_PORT)
        }
    }
}

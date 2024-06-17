package com.example.kopringworkshop.test.testcontainers

import org.junit.jupiter.api.Test

class DummyTest: AbstractUrlShortenerApiApplicationTests() {
    @Test
    fun `mysql information`() {
        println("mysqlContainer.jdbcUrl = ${mysqlContainer.jdbcUrl}")
        println("mysqlContainer.username = ${mysqlContainer.username}")
        println("mysqlContainer.password = ${mysqlContainer.password}")
    }
}

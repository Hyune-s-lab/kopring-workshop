package com.example.kopringworkshop.mysqldb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MysqlDBApplication {
}

fun main(vararg args: String) {
    runApplication<MysqlDBApplication>(*args)
}

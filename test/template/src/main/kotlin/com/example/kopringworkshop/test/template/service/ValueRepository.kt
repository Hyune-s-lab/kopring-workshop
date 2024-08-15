package com.example.kopringworkshop.test.template.service

class ValueRepository(
    private var current: Int = 0
) {
    fun add(value: Int) {
        current += value
    }

    fun get(): Int {
        return current
    }
}

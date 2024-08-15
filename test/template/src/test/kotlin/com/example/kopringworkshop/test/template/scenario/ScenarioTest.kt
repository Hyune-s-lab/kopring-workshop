package com.example.kopringworkshop.test.template.scenario

import com.example.kopringworkshop.test.template.service.ValueRepository
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import kotlin.random.Random

class ScenarioTest : ScenarioTestSupport() {

    private val sut = ValueRepository()
    private var expected = 0

    @Test
    fun `A1) 현재 값 확인`() {
        expected = sut.get()
        println("### 초기값: $expected")
    }

    @RepeatedTest(3)
    fun `A2) 무작위 값 덧셈 후 확인 1`() {
        val addValue = Random.nextInt(-100, 100)
        expected += addValue

        sut.add(addValue)

        sut.get() shouldBe expected
        println("### 덧셈: $addValue, 현재값: $expected")
    }

    @RepeatedTest(3)
    fun `A3) 무작위 값 덧셈 후 확인 2`() {
        val addValue = Random.nextInt(-100, 100)
        expected += addValue

        sut.add(addValue)

        sut.get() shouldBe expected
        println("### 덧셈 값: $addValue, 덧셈 후 현재값: $expected")
    }

    @RepeatedTest(3)
    fun `A4) 무작위 값 덧셈 후 확인 3`() {
        val addValue = Random.nextInt(-100, 100)
        expected += addValue

        sut.add(addValue)

        sut.get() shouldBe expected
        println("### 덧셈 값: $addValue, 현재값: $expected")
    }
}

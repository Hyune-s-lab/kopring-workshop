package com.example.kopringworkshop.externalapi.wiremock.httpclient

import com.example.kopringworkshop.externalapi.wiremock.AbstractWiremockApplication
import feign.RetryableException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.shouldContain
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import kotlin.random.Random

class DummyApiClientTest(
    @Autowired private val dummyApiClient: DummyApiClient
) : AbstractWiremockApplication() {

    @Test
    fun `GET movies api 를 호출하면 movie 목록을 반환한다`() {
        val movies = dummyApiClient.getMovies()

        movies shouldNotBe null

        log.debug("Movies: {}", movies)
    }

    @Test
    fun `GET movies_{movieId} api 를 호출하면 movie 를 반환한다`() {
        val movieId = Random.nextLong(1, 100)
        val movie = dummyApiClient.getMovieById(movieId)

        movie shouldNotBe null

        log.debug("Movie: {}", movie)
    }

    @Test
    fun `GET movies_{movieId}_4sec api 를 호출하면 read timeout 예외가 발생한다`() {
        val movieId = Random.nextLong(1, 100)

        val exception = shouldThrow<RetryableException> { dummyApiClient.getMovieByIdMockDelay4sec(movieId) }
        exception.message shouldContain "Read timed out"
    }
}

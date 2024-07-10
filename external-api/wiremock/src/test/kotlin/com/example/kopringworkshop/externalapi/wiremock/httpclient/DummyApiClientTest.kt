package com.example.kopringworkshop.externalapi.wiremock.httpclient

import com.example.kopringworkshop.externalapi.wiremock.AbstractWiremockApplication
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import kotlin.random.Random

class DummyApiClientTest(
    @Autowired private val dummyApiClient: DummyApiClient
) : AbstractWiremockApplication() {

    @Test
    fun `getMovies should return list of movies`() {
        val movies = dummyApiClient.getMovies()

        movies shouldNotBe null

        log.debug("Movies: {}", movies)
    }

    @Test
    fun `getMovieById should return movie by id`() {
        val movieId = Random.nextLong(1, 100)
        val movie = dummyApiClient.getMovieById(movieId)

        movie shouldNotBe null

        log.debug("Movie: {}", movie)
    }
}

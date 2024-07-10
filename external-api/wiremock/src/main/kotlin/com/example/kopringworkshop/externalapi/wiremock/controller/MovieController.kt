package com.example.kopringworkshop.externalapi.wiremock.controller

import com.example.kopringworkshop.externalapi.wiremock.httpclient.DummyApiClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class MovieController(
    private val dummyApiClient: DummyApiClient
) {
    @GetMapping("/movies")
    fun getMovies(): List<Any> {
        return dummyApiClient.getMovies()
    }

    @GetMapping("/movies/{movieId}")
    fun getMovieById(@PathVariable movieId: Long): Any {
        return dummyApiClient.getMovieById(movieId)
    }
}

package com.example.kopringworkshop.externalapi.wiremock.httpclient

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient(value = "dummy-api", url = "\${spring.cloud.openfeign.client.config.dummy-api.url}")
interface DummyApiClient {
    @RequestMapping(method = [RequestMethod.GET], value = ["/movies"])
    fun getMovies(): List<Any>

    @RequestMapping(method = [RequestMethod.GET], value = ["/movies/{movieId}"], produces = ["application/json"])
    fun getMovieById(@PathVariable movieId: Long): Any

    @RequestMapping(method = [RequestMethod.GET], value = ["/movies/{movieId}/4sec"], produces = ["application/json"])
    fun getMovieByIdMockDelay4sec(@PathVariable movieId: Long): Any
}

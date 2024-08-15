package com.example.kopringworkshop.test.template.web

import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class WebTest(
    private val mockMvc: MockMvc,
) : WebTestSupport() {
    @Test
    fun `health check`() {
        val response = mockMvc.perform(
            get("/health")
        )
            .andExpect(status().isOk)
            .andReturn()
            .run {
                this.response.contentAsString
            }
        log.debug("### result: $response")
    }
}

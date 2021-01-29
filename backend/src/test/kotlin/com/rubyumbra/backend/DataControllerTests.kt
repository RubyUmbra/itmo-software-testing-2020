package com.rubyumbra.backend

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DataControllerTests(
    @Autowired
    private val restTemplateBuilder: RestTemplateBuilder,
    @LocalServerPort
    private val port: Int
) {
    @Test
    fun testHomePageIsPublic() {
        val restTemplate: RestTemplate = restTemplateBuilder.build()
        val response: ResponseEntity<String> =
            restTemplate.getForEntity("http://localhost:$port/api/data/home", String::class.java)
        assertEquals("Home page", response.body)
    }
}

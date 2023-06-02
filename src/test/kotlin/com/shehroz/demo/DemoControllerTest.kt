package com.shehroz.demo

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebFluxTest
class DemoControllerTest {
    @Autowired
    private lateinit var webTestClient: WebTestClient
    private val demoService = mockk<DemoService>()
    val demoController = DemoController(demoService)

    @Test
    @DisplayName("Should Get All Users")
    fun shouldGetAllUsers() {
        // Arrange
        val expected = mutableListOf<GetAllUsersDTO>(
            GetAllUsersDTO("shehroz.ali", 3352669779),
            GetAllUsersDTO("saad.hashim", 3022194551),
        )
        every { demoService.getAllUsers() } returns expected

        // Act and Assert
        webTestClient
            .get()
            .uri("/api/v1/users/all")
            .exchange()
            .expectStatus().isOk()
            .expectBody<List<GetAllUsersDTO>>()
            .isEqualTo(expected)
    }
}
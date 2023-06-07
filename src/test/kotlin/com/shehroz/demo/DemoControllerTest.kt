package com.shehroz.demo

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import java.util.UUID

@WebFluxTest(DemoController::class)
class DemoControllerTest(@Autowired private val webTestClient: WebTestClient) {

    @MockBean
    lateinit var demoService: DemoService

    @Test
    @DisplayName("Should Get All Users")
    fun shouldGetAllUsers() {
        // Arrange
        val expected = mutableListOf<UserDTO>(
            UserDTO("shehroz.ali", 3352669779),
            UserDTO("saad.hashim", 3022194551),
        )
        `when`(demoService.getAllUsers()).thenReturn(expected)

        // Act & Assert
        webTestClient
            .get()
            .uri("/api/v1/users")
            .exchange()
            .expectStatus().isOk()
            .expectBody<List<UserDTO>>()
            .isEqualTo(expected)
        verify(demoService, Mockito.times(1)).getAllUsers()
    }

    @Test
    @DisplayName("Should Get A User By UUID")
    fun shouldGetUserByUUID() {
        // Arrange
        val savedUserId = UUID.randomUUID()
        val expected = UserDTO("Shehroz Ali", 3352669779)
        `when`(demoService.getUserByUUID(savedUserId)).thenReturn(expected)

        // Act & Assert
        webTestClient
            .get()
            .uri("/api/v1/users/{userId}", savedUserId)
            .exchange()
            .expectStatus().isOk()
            .expectBody<UserDTO>()
            .isEqualTo(expected)
        verify(demoService, Mockito.times(1))
            .getUserByUUID(userId = savedUserId)
    }
}

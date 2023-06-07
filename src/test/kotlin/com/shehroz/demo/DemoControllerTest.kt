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

        // Act
        webTestClient
            .get()
            .uri("/api/v1/users/all")
            .exchange()
            .expectStatus().isOk()
            .expectBody<List<UserDTO>>()
            .isEqualTo(expected)

        // Assert
        verify(demoService, Mockito.times(1)).getAllUsers()
    }

    @Test
    @DisplayName("Should Get A User By UUID")
    fun shouldGetAUserByUUID() {

    }
}

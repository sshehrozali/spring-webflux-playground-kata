package com.shehroz.demo

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.util.UUID

@WebFluxTest(UserController::class)
class UserControllerTest(@Autowired private val webTestClient: WebTestClient) {

    @MockkBean
    private lateinit var userService: UserService

    @Test
    fun `should return 200 if all users were fetched successfully`() {
        // Arrange
        val expected = mutableListOf<UserDTO>(
            UserDTO("shehroz.ali", 3352669779),
            UserDTO("saad.hashim", 3022194551),
        )
        every { userService.getAllUsers() } returns Mono.just(expected)

        // Act & Assert
        webTestClient
            .get()
            .uri("/api/v1/users")
            .exchange()
            .expectStatus().isOk()
            .expectBody<List<UserDTO>>()
            .isEqualTo(expected)
    }

    @Test
    fun `should return HTTP 500 if something goes wrong`() {
        `when`(userService.getAllUsers()).thenThrow(RuntimeException())
        webTestClient
            .get()
            .uri("/api/v1/users")
            .exchange()
            .expectStatus().is5xxServerError
        verify(userService, Mockito.times(1)).getAllUsers()
    }

    @Test
    fun `should get a user by valid UUID if user exists`() {
        val savedUserId = UUID.randomUUID()
        val expected = UserDTO(
            "Shehroz",
            3352669779
        )
        `when`(userService.getUserByUUID(savedUserId)).thenReturn(Mono.just(expected))

        webTestClient
            .get()
            .uri("/api/v1/users/{userId}", savedUserId)
            .exchange()
            .expectStatus().isOk()
            .expectBody<UserDTO>()
            .isEqualTo(expected)
        verify(userService, Mockito.times(1))
            .getUserByUUID(userId = savedUserId)
    }

    @Test
    fun `should return 404 not found if user doesn't exist by UUID`() {
        val userId = UUID.randomUUID()
        `when`(userService.getUserByUUID(userId)).thenReturn(Mono.error(UserNotFoundException()))

        webTestClient
            .get()
            .uri("/api/v1/users/{userId}", userId)
            .exchange()
            .expectStatus().isNotFound
            .expectBody<Void>()
        verify(userService, Mockito.times(1))
            .getUserByUUID(userId = userId)
    }

    @Test
    fun `should throw exception if UUID passed for getting a user is invalid`() {
        val userId = UUID.fromString("00000000-0000-0000-0000-000000000000")
        `when`(userService.getUserByUUID(userId)).thenReturn(Mono.error(InvalidUUIDException()))

        webTestClient
            .get()
            .uri("/api/v1/users/{userId}", userId)
            .exchange()
            .expectStatus().isBadRequest
            .expectBody<Void>()
        verify(userService, Mockito.times(1))
            .getUserByUUID(userId = userId)
    }
}

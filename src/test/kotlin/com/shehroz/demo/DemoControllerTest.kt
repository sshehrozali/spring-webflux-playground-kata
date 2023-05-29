package com.shehroz.demo

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.assertj.core.api.Assertions.assertThat
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody


@WebFluxTest(controllers = [DemoController::class])
@ExtendWith(SpringExtension::class)
class DemoControllerTest {
    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    @DisplayName("Should Get All Users")
    fun shouldGetAllUsers() {
        // Arrange, Act, Assert
        webTestClient
            .get()
            .uri("/api/v1/users/all")
            .exchange()
            .expectStatus().isOk()
            .expectBody<String>()
            .isEqualTo("Hello Kotlin Spring WebFlux")
    }
}
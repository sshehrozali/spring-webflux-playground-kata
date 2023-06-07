package com.shehroz.demo

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.*

class DemoServiceTest {

    private val demoRepository: DemoRepository = mockk()
    private val serviceUnderTest = DemoService(demoRepository)

    @Test
    @DisplayName("Should Get All Users Successfully")
    fun shouldGetAllUsersSuccessfully() {
        // Arrange
        val savedUsers = mutableListOf<User>(
            User(
                UUID.randomUUID(),
                UUID.randomUUID(),
                "shehroz",
                3352669779
            )
        )
        every { demoRepository.findAll() } returns savedUsers

        val expected = mutableListOf<UserDTO>(
            UserDTO(
                "shehroz",
                3352669779
            )
        )

        // Act
        val actual = serviceUnderTest.getAllUsers()

        // Assert
        assertThat(actual).isEqualTo(expected)
    }
}
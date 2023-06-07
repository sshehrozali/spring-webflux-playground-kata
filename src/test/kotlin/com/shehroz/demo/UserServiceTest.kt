package com.shehroz.demo

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.*

class UserServiceTest {

    private val userRepository: UserRepository = mockk()
    private val serviceUnderTest = UserService(userRepository)

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
        every { userRepository.findAll() } returns savedUsers

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
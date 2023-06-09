package com.shehroz.demo

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.anyOf
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.mockito.Mockito.any
import org.mockito.Mockito.`when`
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

    @Test
    @DisplayName("Should Get User Successfully by UUID")
    fun shouldGetUserSuccessfullyByUUID() {
        // Arrange
        val savedUserId = UUID.randomUUID()
        val savedUser = User(
            UUID.randomUUID(),
            savedUserId,
            "Shehroz",
            3352669779
        )
        every { userRepository.findByUserId(any()) } returns Optional.of(savedUser)

        val expected = UserDTO(
            "Shehroz",
            3352669779
        )

        // Act
        val actual = serviceUnderTest.getUserByUUID(savedUserId)

        // Assert
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    @DisplayName("Should Throw Exception If No User Found By UUID")
    fun shouldThrowExceptionIfNoUserFoundByUUID() {
        // Arrange
        val savedUserId = UUID.randomUUID()
        every { userRepository.findByUserId(savedUserId) } returns Optional.empty()

        // Act & Assert
        assertThrows<IllegalAccessError> { serviceUnderTest.getUserByUUID(savedUserId) }
    }
}
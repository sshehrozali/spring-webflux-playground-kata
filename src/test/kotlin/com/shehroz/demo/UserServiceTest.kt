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
import reactor.core.publisher.Mono
import reactor.kotlin.test.expectError
import reactor.test.StepVerifier
import java.util.*

class UserServiceTest {

    private val userRepository: UserRepository = mockk()
    private val serviceUnderTest = UserService(userRepository)

    @Test
    fun `should get all users successfully`() {
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

        val result = serviceUnderTest.getAllUsers()
        StepVerifier.create(result)
            .expectNext(expected)
            .verifyComplete()
    }

    @Test
    fun `should get a user successfully by valid UUID if user exists`() {
        val savedUserId = UUID.randomUUID()
        val savedUser = User(
            UUID.randomUUID(),
            savedUserId,
            "Shehroz",
            3352669779
        )
        every { userRepository.findByUserId(any()) } returns Optional.of(savedUser)
        val expected = UserDTO(savedUser.userName, savedUser.phoneNumber)

        val result = serviceUnderTest.getUserByUUID(savedUserId)
        StepVerifier.create(result)
            .expectNext(expected)
            .verifyComplete()
    }

    @Test
    fun `should throw UserNotFoundException if no user found by UUID`() {
        val savedUserId = UUID.randomUUID()
        every { userRepository.findByUserId(savedUserId) } returns Optional.empty()

        val result = serviceUnderTest.getUserByUUID(savedUserId)
        StepVerifier.create(result)
            .expectError<UserNotFoundException>()
            .verify()
    }

    @Test
    fun `should throw InvalidUUIDException if UUID passed is invalid`() {
        val result = serviceUnderTest.getUserByUUID(UUID.fromString("00000000-0000-0000-0000-000000000000"))
        StepVerifier.create(result)
            .expectError<InvalidUUIDException>()
            .verify()
    }
}
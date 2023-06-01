package com.shehroz.demo

import io.mockk.every
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import java.util.*

class DemoServiceTest(
    @Mock private val demoRepository: DemoRepository,
    @InjectMocks private val serviceUnderTest: DemoService,
) {
    @Test
    @DisplayName("Should Get All Users Successfully")
    fun shouldGetAllUsersSuccessfully() {
        // Arrange
        val savedUsers = mutableListOf<DemoEntity>(
            DemoEntity(UUID.randomUUID(), "shehroz", 3352669779)
        )
        every { demoRepository.findAll() } returns savedUsers

        val expected = mutableListOf<GetAllUsersDTO>(
            GetAllUsersDTO("shehroz", 3352669779)
        )

        // Act
        val actual = serviceUnderTest.getAllUsers()

        // Assert
        assertThat(actual).isEqualTo(expected)
    }
}
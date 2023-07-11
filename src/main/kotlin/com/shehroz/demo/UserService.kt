package com.shehroz.demo

import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException
import java.util.UUID

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun getAllUsers(): List<UserDTO> {
        return userRepository
            .findAll()
            .stream()
            .map { it -> UserDTO(it.userName, it.phoneNumber) }
            .toList()
    }

    fun getUserByUUID(userId: UUID): UserDTO {
        if (userId.equals(UUID.fromString("00000000-0000-0000-0000-000000000000"))) {
            throw IllegalArgumentException("Invalid UUID passed")
        }

        val user = userRepository.findByUserId(userId)

        if (user.isEmpty) {
            throw IllegalAccessError("User not found by UUID")
        }

        return UserDTO(
            user.get().userName,
            user.get().phoneNumber
        )
    }
}
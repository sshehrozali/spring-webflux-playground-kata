package com.shehroz.demo

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.lang.IllegalArgumentException
import java.lang.RuntimeException
import java.util.Optional
import java.util.UUID

@Service
class UserService(
    private val userRepository: UserRepository
) {
    private val falseUUID: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000")

    fun getAllUsers(): Mono<List<UserDTO>> {
        return Mono.fromCallable {
            userRepository.findAll()
                .map { user ->
                    UserDTO(user.userName, user.phoneNumber)
                }
                .toList()
        }
    }

    fun getUserByUUID(userId: UUID): Mono<UserDTO> {
        return Mono.fromCallable {
            if (userId.equals(falseUUID)) {
                throw InvalidUUIDException()
            }

            val user = userRepository.findByUserId(userId)
            if (user.isEmpty) {
                throw UserNotFoundException()
            }

            UserDTO(user.get().userName, user.get().phoneNumber)
        }
    }
}
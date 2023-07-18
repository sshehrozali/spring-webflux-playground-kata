package com.shehroz.demo.service

import com.shehroz.demo.dto.UserDTO
import com.shehroz.demo.repository.UserRepository
import com.shehroz.demo.exception.InvalidUUIDException
import com.shehroz.demo.exception.UserNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.UUID

@Service
class UserService(
    private val userRepository: UserRepository
) {
    val logger = LoggerFactory.getLogger(this::class.java)

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
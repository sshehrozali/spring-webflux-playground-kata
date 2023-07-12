package com.shehroz.demo

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.lang.IllegalArgumentException
import java.util.Optional
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

    fun getUserByUUID(userId: UUID): Mono<UserDTO> {
        return Mono.just(userId)
            .map {
                val user = userRepository.findByUserId(userId)
                if (user.isEmpty) {
                    throw IllegalAccessException("User not found by UUID")
                }

                UserDTO(user.get().userName, user.get().phoneNumber)
            }
            .onErrorResume(IllegalAccessException::class.java) {
                Mono.empty()
            }


//        if (userId.equals(UUID.fromString("00000000-0000-0000-0000-000000000000"))) {
//            throw IllegalArgumentException("Invalid UUID passed")
//        }
//
//        val user = userRepository.findByUserId(userId)
//
//        if (user.isEmpty) {
//            throw IllegalAccessError("User not found by UUID")
//        }
//
//        return Mono.just(
//            UserDTO(
//                user.get().userName,
//                user.get().phoneNumber
//            )
//        )
    }
}
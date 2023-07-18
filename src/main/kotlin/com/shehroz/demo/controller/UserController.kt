package com.shehroz.demo.controller

import com.shehroz.demo.exception.InvalidUUIDException
import com.shehroz.demo.dto.UserDTO
import com.shehroz.demo.exception.UserNotFoundException
import com.shehroz.demo.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.util.UUID

@RestController
@RequestMapping("api/v1")
class UserController(
    private val userService: UserService
) {
    @GetMapping("/users")
    fun getAllUsers(): Mono<ResponseEntity<List<UserDTO>>> {
        return userService.getAllUsers()
            .map { ResponseEntity.ok().body(it) }
            .onErrorResume { error ->
                val status = when (error) {
                    is RuntimeException -> HttpStatus.INTERNAL_SERVER_ERROR
                    else -> HttpStatus.INTERNAL_SERVER_ERROR
                }
                Mono.just(ResponseEntity.status(status).build())
            }
    }

    @GetMapping("/users/{userId}")
    fun getUserByUUID(@PathVariable userId: UUID): Mono<ResponseEntity<UserDTO>> {
        return userService.getUserByUUID(userId)
            .map { ResponseEntity.ok().body(it) }
            .onErrorResume { error ->
                val status = when (error) {
                    is UserNotFoundException -> HttpStatus.NOT_FOUND
                    is InvalidUUIDException -> HttpStatus.BAD_REQUEST
                    is RuntimeException -> HttpStatus.INTERNAL_SERVER_ERROR
                    else -> HttpStatus.INTERNAL_SERVER_ERROR
                }
                Mono.just(ResponseEntity.status(status).build())
            }
    }
}
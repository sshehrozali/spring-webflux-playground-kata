package com.shehroz.demo

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
        val allUsers = userService.getAllUsers()
        return Mono.just(
            ResponseEntity
                .ok()
                .body(allUsers)
        )
    }

    @GetMapping("/users/{userId}")
    fun getUserByUUID(@PathVariable userId: UUID): Mono<ResponseEntity<UserDTO>> {
        return Mono
            .just(userId)
            .flatMap { userService.getUserByUUID(userId) }
            .map { ResponseEntity.ok().body(it) }
    }
}
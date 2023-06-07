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
class DemoController(
    private val demoService: DemoService
) {
    @GetMapping("/users/all")
    fun getAllUsers(): Mono<ResponseEntity<List<UserDTO>>> {
        val allUsers = demoService.getAllUsers()
        return Mono.just(
            ResponseEntity
                .ok()
                .body(allUsers)
        )
    }

    @GetMapping("/users/{userId}")
    fun getUserByUUID(@PathVariable userId: UUID): Mono<ResponseEntity<UserDTO>> {
        val user = demoService.getUserByUUID(userId)
        return Mono.just(
            ResponseEntity
                .ok()
                .body(user)
        )
    }
}
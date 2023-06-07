package com.shehroz.demo

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

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
}
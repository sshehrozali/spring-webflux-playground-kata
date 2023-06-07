package com.shehroz.demo

import org.springframework.stereotype.Service

@Service
class DemoService(
    private val demoRepository: DemoRepository
) {
    fun getAllUsers(): List<UserDTO> {
        return demoRepository
            .findAll()
            .stream()
            .map { it -> UserDTO(it.userName, it.phoneNumber) }
            .toList()
    }

    fun getUserByUUID(): UserDTO {
        demoRepository.findB
    }
}
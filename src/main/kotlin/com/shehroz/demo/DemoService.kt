package com.shehroz.demo

import org.springframework.stereotype.Service

@Service
class DemoService(
    private val demoRepository: DemoRepository
) {
    fun getAllUsers(): List<GetAllUsersDTO> {

    }
}
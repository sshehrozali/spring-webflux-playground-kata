package com.shehroz.demo

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "users")
class DemoEntity(
    @Id
    var id: UUID = UUID.randomUUID(),

    @Column(name = "user_id", nullable = false)
    var userId: UUID,

    @Column(name = "user_name", nullable = false)
    var userName: String,

    @Column(name = "phone_number", nullable = true)
    var phoneNumber: Long
) : java.io.Serializable
package com.example.kopringworkshop.test.testcontainers.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "person")
@Entity
class PersonEntity(
    val lastName: String,
    val firstName: String,
    val age: Int,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}

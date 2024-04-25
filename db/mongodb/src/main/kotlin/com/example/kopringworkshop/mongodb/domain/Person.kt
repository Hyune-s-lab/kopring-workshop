package com.example.kopringworkshop.mongodb.domain

import org.springframework.data.annotation.Id
import java.io.Serializable

data class Person(
    val firstname: String,
    val lastname: String,
    val age: Int,

    @Id val id: String? = null,
): Serializable

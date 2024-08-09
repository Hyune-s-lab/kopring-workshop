package com.example.kopringworkshop.scenario1.entity

import jakarta.persistence.*

@Entity
class Member(
    val name: String,
    val email: String,

    @ElementCollection
    val termOfServiceAgreeds: MutableSet<TermOfServiceAgreed> = mutableSetOf(),
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}

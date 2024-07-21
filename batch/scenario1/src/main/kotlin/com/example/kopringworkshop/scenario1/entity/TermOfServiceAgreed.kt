package com.example.kopringworkshop.scenario1.entity

import com.example.kopringworkshop.scenario1.model.Service
import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

@Embeddable
class TermOfServiceAgreed(
    @Enumerated(EnumType.STRING)
    val service: Service,
    @Enumerated(EnumType.STRING)
    val version: Service.TermVersion
)

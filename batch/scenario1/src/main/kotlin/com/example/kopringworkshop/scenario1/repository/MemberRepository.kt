package com.example.kopringworkshop.scenario1.repository

import com.example.kopringworkshop.scenario1.entity.Member
import com.example.kopringworkshop.scenario1.entity.TermOfServiceAgreed
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
    fun countByTermOfServiceAgreedsContaining(termOfServiceAgreed: TermOfServiceAgreed): Long
    fun findAllByTermOfServiceAgreedsContaining(termOfServiceAgreed: TermOfServiceAgreed): List<Member>
}

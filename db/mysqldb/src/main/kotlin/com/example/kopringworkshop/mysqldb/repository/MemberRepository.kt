package com.example.kopringworkshop.mysqldb.repository

import com.example.kopringworkshop.mysqldb.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
    fun findAllByTeamId(teamId: Long): List<Member>
}

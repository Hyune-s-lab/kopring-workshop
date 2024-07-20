package com.example.kopringworkshop.mysqldb.service

import com.example.kopringworkshop.mysqldb.dto.MemberDto
import com.example.kopringworkshop.mysqldb.entity.Member
import com.example.kopringworkshop.mysqldb.repository.MemberRepository
import com.example.kopringworkshop.mysqldb.repository.TeamRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
@Transactional
class MemberService(
    private val memberRepository: MemberRepository,
    private val teamRepository: TeamRepository
) {
    fun createMember(memberDto: MemberDto): Member {
        val team = teamRepository.findById(memberDto.teamId).orElseThrow { EntityNotFoundException("Team not found") }
        val member = Member(
            name = memberDto.name,
            team = team,
            joinedAt = Instant.now()
        )
        return memberRepository.save(member)
    }

    @Transactional(readOnly = true)
    fun getMember(id: Long): Member {
        return memberRepository.findById(id).orElseThrow { EntityNotFoundException("Member not found") }
    }

    fun changeTeam(memberId: Long, newTeamId: Long) {
        val member = memberRepository.findById(memberId).orElseThrow { EntityNotFoundException("Member not found") }
        val newTeam = teamRepository.findById(newTeamId).orElseThrow { EntityNotFoundException("Team not found") }
        member.changeTeam(newTeam)
        memberRepository.save(member)
    }
}

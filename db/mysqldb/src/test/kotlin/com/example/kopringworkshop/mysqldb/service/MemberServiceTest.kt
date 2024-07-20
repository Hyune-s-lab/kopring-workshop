package com.example.kopringworkshop.mysqldb.service

import com.example.kopringworkshop.mysqldb.TestDefaultSupport
import com.example.kopringworkshop.mysqldb.dto.MemberDto
import com.example.kopringworkshop.mysqldb.entity.Member
import com.example.kopringworkshop.mysqldb.entity.Team
import com.example.kopringworkshop.mysqldb.repository.MemberRepository
import com.example.kopringworkshop.mysqldb.repository.TeamRepository
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.RepeatedTest
import org.springframework.beans.factory.annotation.Autowired
import java.time.Instant

class MemberServiceTest(
    @Autowired private val sut: MemberService,

    @Autowired private val teamRepository: TeamRepository,
    @Autowired private val memberRepository: MemberRepository
) : TestDefaultSupport() {

    @RepeatedTest(10)
    fun `멤버 생성 테스트`() {
        val memberName = faker.name().fullName()
        val team = Team(name = faker.team().name())
        teamRepository.save(team)
        val memberDto = MemberDto(name = memberName, teamId = team.id!!)

        val member = sut.createMember(memberDto)

        member.id shouldNotBe null
        member.name shouldBe memberName
        member.team!!.id shouldBe team.id
    }

    @RepeatedTest(10)
    fun `멤버 조회 테스트`() {
        val memberName = faker.name().fullName()
        val team = Team(name = faker.team().name())
        teamRepository.save(team)
        val member = Member(name = memberName, team = team, joinedAt = Instant.now())
        memberRepository.save(member)

        val foundMember = sut.getMember(member.id!!)

        foundMember.id shouldBe member.id
        foundMember.name shouldBe memberName
        foundMember.team!!.id shouldBe team.id
    }

    @RepeatedTest(10)
    fun `멤버 팀 변경 테스트`() {
        // 팀, 멤버 생성
        val originTeam = Team(name = faker.team().name())
        val newTeam = Team(name = faker.team().name())

        teamRepository.save(originTeam)
        teamRepository.save(newTeam)

        val originTeamMembers = (1..5).map {
            val member = Member(name = "team1 " + faker.name().fullName(), team = originTeam, joinedAt = Instant.now())
            originTeam.addMember(member)
            member
        }
        val newTeamMembers = (1..3).map {
            val member = Member(name = "team2 " + faker.name().fullName(), team = newTeam, joinedAt = Instant.now())
            newTeam.addMember(member)
            member
        }

        teamRepository.save(originTeam)
        teamRepository.save(newTeam)

        // 원래 팀에서 새로운 팀으로 이동할 멤버 선택
        val memberToMove = teamRepository.findById(originTeam.id!!).get().members.last()

        // 멤버 이동 수행
        sut.changeTeam(memberToMove.id!!, newTeam.id!!)

        // 멤버 검증
        val updatedMember = memberRepository.findById(memberToMove.id!!).get()
        updatedMember.team!!.id shouldBe newTeam.id

        // 팀 검증
        val updatedOriginTeam = teamRepository.findById(originTeam.id!!).get()
        val updatedNewTeam = teamRepository.findById(newTeam.id!!).get()

        updatedOriginTeam.members shouldHaveSize 4
        updatedNewTeam.members shouldHaveSize 4

        updatedOriginTeam.members.any { it.id == memberToMove.id && it.name == memberToMove.name } shouldBe false
        updatedNewTeam.members.any { it.id == memberToMove.id && it.name == memberToMove.name } shouldBe true
    }
}

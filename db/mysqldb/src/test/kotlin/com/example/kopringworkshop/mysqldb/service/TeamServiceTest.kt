package com.example.kopringworkshop.mysqldb.service

import com.example.kopringworkshop.mysqldb.TestDefaultSupport
import com.example.kopringworkshop.mysqldb.dto.TeamDto
import com.example.kopringworkshop.mysqldb.entity.Team
import com.example.kopringworkshop.mysqldb.repository.TeamRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import jakarta.persistence.EntityNotFoundException
import org.junit.jupiter.api.RepeatedTest
import org.springframework.beans.factory.annotation.Autowired

class TeamServiceTest(
    @Autowired private val sut: TeamService,

    @Autowired private val teamRepository: TeamRepository
) : TestDefaultSupport() {

    @RepeatedTest(10)
    fun `팀 생성 테스트`() {
        val teamName = faker.team().name()
        val teamDto = TeamDto(name = teamName)

        val team = sut.createTeam(teamDto)

        team.id shouldNotBe null
        team.name shouldBe teamName
    }

    @RepeatedTest(10)
    fun `팀 조회 테스트`() {
        val teamName = faker.team().name()
        val team = Team(name = teamName)
        teamRepository.save(team)

        val foundTeam = sut.getTeam(team.id!!)

        foundTeam.id shouldBe team.id
        foundTeam.name shouldBe teamName
    }

    @RepeatedTest(10)
    fun `팀 업데이트 테스트`() {
        val expectedTeamName = faker.team().name()
        val team = Team(name = faker.team().name())
        teamRepository.save(team)
        val teamDto = TeamDto(name = expectedTeamName)

        val updatedTeam = sut.updateTeam(team.id!!, teamDto)

        updatedTeam.id shouldBe team.id
        updatedTeam.name shouldBe expectedTeamName
    }

    @RepeatedTest(10)
    fun `이름으로 팀 찾기 테스트`() {
        val teamName = faker.team().name()
        val team = Team(name = teamName)
        teamRepository.save(team)

        val foundTeam = sut.getTeamByName(teamName)

        foundTeam.id shouldNotBe null
        foundTeam.name shouldBe teamName
    }

    @RepeatedTest(10)
    fun `존재하지 않는 이름으로 팀 찾기 테스트`() {
        val teamName = "non-existing-team"

        val exception = shouldThrow<EntityNotFoundException> {
            sut.getTeamByName(teamName)
        }
        exception.message shouldBe "Team not found"
    }
}

package com.example.kopringworkshop.mysqldb.service

import com.example.kopringworkshop.mysqldb.dto.TeamDto
import com.example.kopringworkshop.mysqldb.entity.Team
import com.example.kopringworkshop.mysqldb.repository.TeamRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TeamService(
    private val teamRepository: TeamRepository,
) {
    fun createTeam(teamDto: TeamDto): Team {
        val team = Team(
            name = teamDto.name
        )
        return teamRepository.save(team)
    }

    @Transactional(readOnly = true)
    fun getTeam(id: Long): Team {
        return teamRepository.findById(id).orElseThrow { EntityNotFoundException("Team not found") }
    }

    fun updateTeam(id: Long, teamDto: TeamDto): Team {
        val team = teamRepository.findById(id).orElseThrow { EntityNotFoundException("Team not found") }
        team.apply {
            name = teamDto.name
        }
        return teamRepository.save(team)
    }
}

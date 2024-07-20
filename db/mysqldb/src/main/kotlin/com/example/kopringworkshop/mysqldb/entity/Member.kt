package com.example.kopringworkshop.mysqldb.entity

import jakarta.persistence.*
import java.time.Instant

@Entity
class Member(
    val name: String,

    @ManyToOne()
    @JoinColumn(name = "team_id")
    var team: Team? = null,

    val joinedAt: Instant
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    init {
        team?.addMember(this)
    }

    fun changeTeam(newTeam: Team) {
        team?.removeMember(this)
        team = newTeam
        newTeam.addMember(this)
    }
}

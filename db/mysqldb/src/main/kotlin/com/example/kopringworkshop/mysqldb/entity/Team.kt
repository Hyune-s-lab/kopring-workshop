package com.example.kopringworkshop.mysqldb.entity

import jakarta.persistence.*

@Entity
class Team(
    var name: String,

    @OneToMany(mappedBy = "team", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    val members: MutableList<Member> = mutableListOf()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    fun addMember(member: Member) {
        if (!members.contains(member)) {
            members.add(member)
            member.team = this
        }
    }

    fun removeMember(member: Member) {
        if (members.contains(member)) {
            members.remove(member)
            member.team = null
        }
    }
}

package com.example.kopringworkshop.mysqldb.repository

import com.example.common.KLogging
import com.example.kopringworkshop.mysqldb.entity.QTeam
import com.example.kopringworkshop.mysqldb.entity.Team
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class TeamQuerydslRepository(
    private val queryFactory: JPAQueryFactory
) {
    fun findByName(name: String): Team? {
        log.info("### [querydsl] findByName: name={}", name)

        val qTeam = QTeam.team

        return queryFactory
            .selectFrom(qTeam)
            .where(qTeam.name.eq(name))
            .fetchOne()
    }

    companion object : KLogging()
}

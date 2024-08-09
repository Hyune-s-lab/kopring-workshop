package com.example.kopringworkshop.scenario1.config.datainit

import com.example.kopringworkshop.scenario1.config.datainit.LocalDataInit.Companion.log
import com.example.kopringworkshop.scenario1.entity.Member
import com.example.kopringworkshop.scenario1.model.Service
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Component
class BatchJobComponent(
    private val jdbcTemplate: JdbcTemplate
) {
    @Transactional
    fun job1(members: List<Member>): Pair<LocalDateTime, LocalDateTime> {
        val dbJob1StartAt = LocalDateTime.now()
        log.debug("### start local data init: job1")

        val sql = "INSERT INTO member (name, email) VALUES (?, ?)"
        val batchArgs = members.map { member ->
            arrayOf<Any>(member.name, member.email)
        }

        jdbcTemplate.batchUpdate(sql, batchArgs)

        val dbJob1EndAt = LocalDateTime.now()
        log.debug("### end local data init: job")

        return Pair(dbJob1StartAt, dbJob1EndAt)
    }

    /**
     * chunked 로 나누긴 헀지만 하나의 트랜잭션에 묶여 있다는 위험이 남아있다.
     */
    @Transactional
    fun job2(agreedMemberIds: List<Long>): Pair<LocalDateTime, LocalDateTime> {
        val job2StartAt = LocalDateTime.now()
        log.debug("### start local data init: job2")

        val sql = "INSERT INTO member_term_of_service_agreeds (member_id,service,version) VALUES (?,?,?)"

        agreedMemberIds.chunked(100)
            .forEach { chunked ->
                val batchArgs = chunked.map { memberId ->
                    arrayOf(memberId, Service.A001.name, Service.TermVersion.V20240701.name)
                }

                jdbcTemplate.batchUpdate(sql, batchArgs)
            }

        val job2EndAt = LocalDateTime.now()
        log.debug("### end local data init: job2")

        return Pair(job2StartAt, job2EndAt)
    }
}

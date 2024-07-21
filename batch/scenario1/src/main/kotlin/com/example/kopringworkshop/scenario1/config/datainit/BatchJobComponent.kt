package com.example.kopringworkshop.scenario1.config.datainit

import com.example.kopringworkshop.scenario1.config.datainit.LocalDataInit.Companion.log
import com.example.kopringworkshop.scenario1.entity.Member
import com.example.kopringworkshop.scenario1.entity.TermOfServiceAgreed
import com.example.kopringworkshop.scenario1.model.Service
import com.example.kopringworkshop.scenario1.repository.MemberRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Component
class BatchJobComponent(
    private val memberRepository: MemberRepository,
) {
    @Transactional
    fun job1(members: List<Member>): Pair<LocalDateTime, LocalDateTime> {
        val dbJob1StartAt = LocalDateTime.now()
        log.debug("### start local data init: job1")

        members.forEach { memberRepository.save(it) }

        val dbJob1EndAt = LocalDateTime.now()
        log.debug("### end local data init: job")

        return Pair(dbJob1StartAt, dbJob1EndAt)
    }

    @Transactional
    fun job2(agreedMemberIds: List<Long>): Pair<LocalDateTime, LocalDateTime> {
        val job2StartAt = LocalDateTime.now()
        log.debug("### start local data init: job2")

        agreedMemberIds.forEach {
            val member = memberRepository.findById(it).get()
            member.termOfServiceAgreeds.add(
                TermOfServiceAgreed(
                    service = Service.A001,
                    version = Service.TermVersion.V20240701,
                )
            )
            memberRepository.save(member)
        }

        val job2EndAt = LocalDateTime.now()
        log.debug("### end local data init: job2")

        return Pair(job2StartAt, job2EndAt)
    }
}

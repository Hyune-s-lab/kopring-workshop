package com.example.kopringworkshop.scenario1.config.datainit

import com.example.common.KLogging
import com.example.kopringworkshop.scenario1.entity.Member
import com.example.kopringworkshop.scenario1.entity.TermOfServiceAgreed
import com.example.kopringworkshop.scenario1.model.Service
import com.example.kopringworkshop.scenario1.repository.MemberRepository
import jakarta.annotation.PostConstruct
import net.datafaker.Faker
import org.springframework.context.annotation.Configuration
import java.time.Duration
import java.time.LocalDateTime

@Configuration
class LocalDataInit(
    private val memberRepository: MemberRepository,

    private val batchJobComponent: BatchJobComponent
) {
    private val faker: Faker = Faker()

    private val memberCount = 50000
    private val agreedMemberCount = 30000

    @PostConstruct
    fun localDataInit() {
        // 1. member 생성
        val members = (1..memberCount).map { Member(faker.name().fullName()) }
        val job1DurationPair = batchJobComponent.job1(members)

        // 2. 생성된 member 중 일부를 약관 동의
        val agreedMemberIds = (1..agreedMemberCount).map { it.toLong() }
        val job2DurationPair = batchJobComponent.job2(agreedMemberIds)

        afterJob(job1DurationPair, job2DurationPair)
    }

    private fun afterJob(
        job1DurationPair: Pair<LocalDateTime, LocalDateTime>,
        job2DurationPair: Pair<LocalDateTime, LocalDateTime>
    ) {
        val memberTotalCount = memberRepository.count()
        val agreedMemberTotalCount = memberRepository.countByTermOfServiceAgreedsContaining(
            TermOfServiceAgreed(
                service = Service.A001,
                version = Service.TermVersion.V20240701
            )
        )
        log.debug(
            "### job1 duration={} millis, {} ~ {}",
            Duration.between(job1DurationPair.first, job1DurationPair.second).toMillis(),
            job1DurationPair.first,
            job1DurationPair.second
        )
        log.debug(
            "### job2 duration={} millis, {} ~ {}",
            Duration.between(job2DurationPair.first, job2DurationPair.second).toMillis(),
            job2DurationPair.first,
            job2DurationPair.second
        )
        log.debug(
            "### total member count: {}, V20240701 agreed member count: {}",
            memberTotalCount,
            agreedMemberTotalCount
        )
    }

    companion object : KLogging()
}

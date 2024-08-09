package com.example.kopringworkshop.scenario1.service

import com.example.common.KLogging
import com.example.kopringworkshop.scenario1.component.AlarmComponent
import com.example.kopringworkshop.scenario1.entity.TermOfServiceAgreed
import com.example.kopringworkshop.scenario1.model.Service.A001
import com.example.kopringworkshop.scenario1.model.Service.TermVersion
import com.example.kopringworkshop.scenario1.repository.MemberRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.LocalDateTime

@Service
class TermOfServiceAgreeService(
    private val memberRepository: MemberRepository,
    private val alarmComponent: AlarmComponent
) {
    fun agree() {
        val startAt = LocalDateTime.now()

        val members = memberRepository
            .findAllByTermOfServiceAgreedsContaining(TermOfServiceAgreed(A001, TermVersion.V20240701))

        runBlocking {
            val jobs = members.map {
                async(Dispatchers.IO) { alarmComponent.sendEmail(it.email) }
                // 가벼운 job 에만 사용되어야 하며 충분한 테스트가 필요한 설정
//                async(Dispatchers.IO.limitedParallelism(10)) { alarmComponent.sendEmail(it.email) }
            }

            with(Thread.getAllStackTraces().keys) {
                val coroutineThreads = filter { it.name.contains("DefaultDispatcher-worker") }
                val nonCoroutineThreads = filter { !it.name.contains("DefaultDispatcher-worker") }
                log.debug(
                    "### coroutineThreads.size={}, nonCoroutineThreads.size={}",
                    coroutineThreads.size,
                    nonCoroutineThreads.size
                )
            }

            jobs.awaitAll()
        }

        val endAt = LocalDateTime.now()

        log.debug(
            "### {} members agreed duration={} millis, {} ~ {}",
            members.size,
            Duration.between(startAt, endAt).toMillis(),
            startAt,
            endAt
        )
    }

    companion object : KLogging()
}

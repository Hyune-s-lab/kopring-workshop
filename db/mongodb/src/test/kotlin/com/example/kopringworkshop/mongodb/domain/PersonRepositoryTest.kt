package com.example.kopringworkshop.mongodb.domain

import com.example.kopringworkshop.mongodb.AbstractMongodbTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import kotlin.random.Random

class PersonRepositoryTest(
    @Autowired private val repository: PersonRepository,
): AbstractMongodbTest() {

    private val log = LoggerFactory.getLogger(this::class.java)

    @Test
    fun `Person 엔티티 저장과 조회가 가능해야 합니다`() {
        val person = repository.save(Person("Walter", "Test", Random.nextInt(50)))
        log.info("### person created: $person")
        assertThat(person.id).isNotNull

        val findPerson = repository.findById(person.id!!).get()
        log.info("### person found: $findPerson")
        assertThat(findPerson).isEqualTo(person)
    }

    @Test
    fun `findByFirstname() 테스트`() {
        val person = repository.save(Person("Walter", "Test", Random.nextInt(50)))
        log.info("### person created: $person")
        assertThat(person.id).isNotNull

        val findPerson = repository.findByFirstname(person.firstname)
        log.info("### person found: $findPerson")
        assertThat(findPerson).isEqualTo(person)

        val notExists = repository.findByFirstname("not-exists")
        log.info("### not exists person: $notExists")
        assertThat(notExists).isNull()
    }

    @Test
    fun `getByFirstname() 테스트`() {
        val person = repository.save(Person("Walter", "Test", Random.nextInt(50)))
        log.info("### person created: $person")
        assertThat(person.id).isNotNull

        val findPerson = repository.getByFirstname(person.firstname)
        log.info("### person found: $findPerson")
        assertThat(findPerson).isEqualTo(person)

        assertThrows<EmptyResultDataAccessException> {
            repository.getByFirstname("not-exists")
        }.printStackTrace()
    }
}

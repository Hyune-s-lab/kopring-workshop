package com.example.kopringworkshop.mongodb.controller

import com.example.kopringworkshop.mongodb.domain.Person
import com.example.kopringworkshop.mongodb.domain.PersonRepository
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.random.Random

@RestController
class PersonController(
    private val personRepository: PersonRepository,
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @PostMapping("/person")
    fun createPerson() {
        val person = personRepository.save(Person("Walter", "White", Random.nextInt(50)))
        log.info("### Person created: $person")
    }

    @GetMapping("/person")
    fun getPerson(): List<Person> {
        return personRepository.findAll().toList()
    }
}

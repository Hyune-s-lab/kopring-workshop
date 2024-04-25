package com.example.kopringworkshop.mongodb.domain

import org.springframework.data.repository.CrudRepository

interface PersonRepository: CrudRepository<Person, String>{
    fun findByFirstname(firstname: String): Person?

    fun getByFirstname(firstname: String): Person
}

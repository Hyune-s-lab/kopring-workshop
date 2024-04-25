package com.example.kopringworkshop.mongodb.domain

import org.springframework.data.repository.CrudRepository

interface PersonRepository: CrudRepository<Person, String>

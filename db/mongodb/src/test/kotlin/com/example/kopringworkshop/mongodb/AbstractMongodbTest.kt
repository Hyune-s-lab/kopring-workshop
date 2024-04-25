package com.example.kopringworkshop.mongodb

import com.example.kopringworkshop.mongodb.domain.Person
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.dropCollection
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@ActiveProfiles("test")
@Testcontainers
@SpringBootTest
abstract class AbstractMongodbTest {
    @Autowired
    lateinit var operations: MongoOperations

    @BeforeEach
    fun beforeEach() {
        operations.dropCollection<Person>()
    }

    companion object {
        @Container
        @JvmField
        var container = MongoDBContainer(DockerImageName.parse("mongo:7"))

        init {
            container.start()
        }

        @DynamicPropertySource
        @JvmStatic
        fun mongoDbProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.data.mongodb.uri") { container.replicaSetUrl }
        }
    }
}

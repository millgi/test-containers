package com.github.millig.testcontainers

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@DataMongoTest
@ContextConfiguration(
    classes = [TestContainersApplication::class],
    initializers = [FruitRepositoryTest.MongoDbInitializer::class]
)
class FruitRepositoryTest {

    companion object {
        @Container
        private val mongo = MongoDbContainer("4.4-bionic")
    }

    @Configuration
    class MongoDbInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
        override fun initialize(applicationContext: ConfigurableApplicationContext) {
            TestPropertyValues.of(
                "spring.data.mongodb.host=${mongo.host}",
                "spring.data.mongodb.port=${mongo.port}"
            ).applyTo(applicationContext.environment)
        }
    }

    @Autowired
    private lateinit var fruitRepository: FruitRepository

    @Test
    internal fun `should store a fruit`() {

        // given
        val fruit = Fruit(id = null, name = "banana", color = "blue")

        // when
        val saved = fruitRepository.save(fruit)

        // then
        assertThat(saved.id).isNotNull()

        assertThat(fruitRepository.findAll())
            .hasSize(1)
            .extracting("name")
            .contains("banana")

    }

}
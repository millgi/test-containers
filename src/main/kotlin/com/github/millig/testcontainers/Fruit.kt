package com.github.millig.testcontainers

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Document
class Fruit(
    @Id var id: String?,
    var name: String,
    var color: String
)

@Component
interface FruitRepository: MongoRepository<Fruit, String>

@Service
class FruitService(private val fruitRepository: FruitRepository) {
    fun save(fruit: Fruit): Fruit = fruitRepository.save(fruit)
    fun findAll(): List<Fruit> = fruitRepository.findAll()
}
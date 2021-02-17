package com.github.millig.testcontainers

import org.testcontainers.containers.GenericContainer
import org.testcontainers.utility.DockerImageName

class MongoDbContainer(version: String): GenericContainer<MongoDbContainer>(DockerImageName.parse("mongo:$version")) {

    init {
        addExposedPort(27017)
    }

    val port get() = getMappedPort(27017)

}
package com.able.data

import io.github.serpro69.kfaker.faker
import java.util.*


open class Faker {
    companion object {
        private val faker = faker {
            fakerConfig {
                random = Random()
                locale = "en"
                uniqueGeneratorRetryLimit = 111
            }
        }

        fun getEmail(): String {
            return faker.internet.email()
        }

        fun getName(): String {
            return "${faker.internet.hashCode()}"
        }
    }
}
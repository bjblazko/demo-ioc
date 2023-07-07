package de.huepattl.demo.ioc

import java.time.LocalDate
import java.util.UUID

data class Person(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val dateOfBirth: LocalDate
)
package de.huepattl.demo.ioc.withintf

import de.huepattl.demo.ioc.Person

interface PersonRepository {

    fun getById(id: String): Result<Person>

}
package de.huepattl.demo.ioc.withfunction

import de.huepattl.demo.ioc.Person

fun getById(id: String, retrieve : (id: String) -> Result<Person>): Result<Person> {
    return retrieve(id)
}
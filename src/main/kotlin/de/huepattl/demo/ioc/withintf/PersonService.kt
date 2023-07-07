package de.huepattl.demo.ioc.withintf

import de.huepattl.demo.ioc.common.Person
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class PersonService(val personRepository: PersonRepository) {

    fun getById(id: String): Result<Person> {
        return personRepository.getById(id)
    }

}
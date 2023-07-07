package de.huepattl.demo.ioc.withmock

import de.huepattl.demo.ioc.Person
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class PersonService(val personRepository: PersonRepository) {

    fun getById(id: String): Result<Person> {
        return personRepository.getById(id)
    }

}
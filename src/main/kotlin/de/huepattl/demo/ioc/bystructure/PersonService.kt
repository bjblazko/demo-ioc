package de.huepattl.demo.ioc.bystructure

import de.huepattl.demo.ioc.Person
import jakarta.enterprise.context.ApplicationScoped
import java.time.LocalDate
import java.time.Period

@ApplicationScoped
class PersonService {

    fun calculateAge(person: Person): Person {
        val period = Period.between(person.dateOfBirth, LocalDate.now())
        return person.copy(age = period.years)
    }

}
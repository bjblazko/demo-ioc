package de.huepattl.demo.ioc.withmock

import de.huepattl.demo.ioc.Person
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Default
import java.time.LocalDate
import java.time.Month

@ApplicationScoped
@Default
class PersonRepository {
    fun getById(id: String): Result<Person> {
        println("using prod repo")
        return when(id) {
            "jane" -> Result.success(Person(id = id, name = "Jane Doe", dateOfBirth = LocalDate.of(1977, Month.MAY, 31)))
            "john" -> Result.success(Person(id = id, name = "John Doe", dateOfBirth = LocalDate.of(1977, Month.DECEMBER, 9)))
            else -> Result.failure(IllegalArgumentException("No person with ID '$id' found"))
        }
    }

}
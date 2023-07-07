package de.huepattl.demo.ioc.withintf

import de.huepattl.demo.ioc.Person
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Alternative
import jakarta.enterprise.inject.Produces
import java.time.LocalDate
import java.time.Month

@ApplicationScoped
class ProvideStubs {

    @Produces
    @Alternative
    @jakarta.annotation.Priority(100)
    fun personRepository(): PersonRepository {
        println("using test repo")
        return PersonRepositoryTestImpl()
    }

}


class PersonRepositoryTestImpl : PersonRepository {
    override fun getById(id: String): Result<Person> {
        return when(id) {
            "jane" -> Result.success(Person(id = id, name = "Jane Doe from TEST", dateOfBirth = LocalDate.of(1977, Month.MAY, 31)))
            "john" -> Result.success(Person(id = id, name = "John Doe from TEST", dateOfBirth = LocalDate.of(1977, Month.DECEMBER, 9)))
            else -> Result.failure(IllegalArgumentException("No person with ID '$id' found in TEST repo"))
        }
    }

}
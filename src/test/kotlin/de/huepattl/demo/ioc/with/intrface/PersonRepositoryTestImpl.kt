package de.huepattl.demo.ioc.with.intrface

import de.huepattl.demo.ioc.common.Person
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Alternative
import jakarta.enterprise.inject.Default
import java.time.LocalDate
import java.time.Month
import javax.annotation.Priority

@ApplicationScoped
@Alternative
@Priority(100)
class PersonRepositoryTestImpl : PersonRepository {
    override fun getById(id: String): Result<Person> {
        println("getById from TEST")
        return when(id) {
            "jane" -> Result.success(Person(id = id, name = "Jane Doe from TEST", dateOfBirth = LocalDate.of(1977, Month.MAY, 31)))
            "john" -> Result.success(Person(id = id, name = "John Doe from TEST", dateOfBirth = LocalDate.of(1977, Month.DECEMBER, 9)))
            else -> Result.failure(IllegalArgumentException("No person with ID '$id' found in TEST repo"))
        }
    }

}
package de.huepattl.demo.ioc.withmock

import de.huepattl.demo.ioc.Person
import jakarta.enterprise.context.ApplicationScoped
import java.time.LocalDate
import java.time.Period

@ApplicationScoped
class PersonService(val personRepository: PersonRepository) {

    fun getByIdAndCalculateAge(id: String): Result<Person> {
        val repoResult = personRepository.getById(id)
        return if (repoResult.isSuccess) {
            val person = repoResult.getOrNull()
            val period = Period.between(person!!.dateOfBirth, LocalDate.now())
            Result.success(person.copy(age = period.years))
        } else {
            repoResult
        }
    }

}
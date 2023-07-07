package de.huepattl.demo.ioc.withfunction

import de.huepattl.demo.ioc.Person
import java.time.LocalDate
import java.time.Period

fun getByIdAndCalculateAge(id: String, retrieve : (id: String) -> Result<Person>): Result<Person> {
    val repoResult = retrieve(id)
    return if (repoResult.isSuccess) {
        val person = repoResult.getOrNull()
        val period = Period.between(person!!.dateOfBirth, LocalDate.now())
        Result.success(person.copy(age = period.years))
    } else {
        repoResult
    }
}
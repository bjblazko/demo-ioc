package de.huepattl.demo.ioc.withmock

import de.huepattl.demo.ioc.Person
import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.Mockito.mock
import java.time.LocalDate

@QuarkusTest
class PersonServiceTest {

    fun getPersonServiceWithMockedRepository(): PersonService {
        val testPerson = Person(id = "jane", name = "Jane TEST", dateOfBirth = LocalDate.now())
        val mockedRepo = mock(PersonRepository::class.java)
        val personResult = Result.success(testPerson)
        Mockito.`when`(mockedRepo.getById(anyString())).thenReturn(personResult)
        return PersonService(mockedRepo)
    }

    @Test
    fun `use service but get data from test repository`() {
        val personService = getPersonServiceWithMockedRepository()
        val personResult = personService.getByIdAndCalculateAge("john")

        assert(personResult.isSuccess)
        // FIXME: why do we have a double result-result here?
        println(personResult.getOrNull()) // Success(Person(id=jane, name=Jane TEST, dateOfBirth=2023-07-07))
    }

}
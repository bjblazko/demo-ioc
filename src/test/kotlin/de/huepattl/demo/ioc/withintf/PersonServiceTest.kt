package de.huepattl.demo.ioc.withintf

import io.quarkus.test.junit.QuarkusTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Test

/**
 * This tests [PersonService], but does NOT autowire its default
 * dependency [PersonRepositoryAppImpl] but uses [PersonRepositoryTestImpl] since it
 * is produced there using producer class [ProvideStubs]
 */
@QuarkusTest
class PersonServiceTest {

    @Inject
    private lateinit var personService: PersonService

    /**
     * @see [ProvideStubs]
     */
    @Test
    fun `use service but get data from test repository`() {
        val person = personService.getByIdAndCalculateAge("john")

        // looks like this: Person(id=john, name=John Doe from TEST, dateOfBirth=1977-12-09)
        assert(person.getOrNull()!!.name.contains("TEST"))
        println(person)
    }

}
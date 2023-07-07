package de.huepattl.demo.ioc.withfunction

import de.huepattl.demo.ioc.Person
import org.junit.jupiter.api.Test
import java.time.LocalDate

/**
 * Here, we do not use CDI at all but provide the according service function with another
 * function as parameter on how to retrieve the person. Thus, we can simply replace the standard function
 * with a local one to this test.
 */
class PersonServiceTest {

    @Test
    fun `test using prod repo function`() {
        val person = getByIdAndCalculateAge("jane", ::getById)

        assert(person.getOrNull()!!.name.equals("Jane Doe"))
    }

    @Test
    fun `test using local function stub`() {
        val person = getByIdAndCalculateAge(
            "jane"
        ) { id: String ->
            Result.success(
                Person(
                    id = "foo",
                    name = "Test",
                    dateOfBirth = LocalDate.now()
                )
            )
        }

        assert(person.getOrNull()!!.name.equals("Test"))
    }

}
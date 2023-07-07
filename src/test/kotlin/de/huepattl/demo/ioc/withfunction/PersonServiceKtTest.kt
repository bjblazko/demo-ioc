package de.huepattl.demo.ioc.withfunction

import de.huepattl.demo.ioc.Person
import org.junit.jupiter.api.Test
import java.time.LocalDate

class PersonServiceKtTest {

    @Test
    fun `test using prod repo`() {
        val person = getById("jane", ::getById)

        assert(person.getOrNull()!!.name.equals("Jane Doe"))
    }

    @Test
    fun `test using stub`() {
        val person = getById("jane", {id: String -> Result.success(Person(id = "foo", name = "Test", dateOfBirth = LocalDate.now()))})

        assert(person.getOrNull()!!.name.equals("Test"))
    }

}
package de.huepattl.demo.ioc.with.intrface

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import jakarta.annotation.Priority
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Alternative
import jakarta.enterprise.inject.Produces
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test

@QuarkusTest
class PersonResourceTest {

    @ApplicationScoped
    class ProvideStubs {

        @Produces
        @Alternative
        @Priority(100)
        fun personRepository(): PersonRepository {
            println("using test repo")
            return PersonRepositoryTestImpl()
        }
    }


    @Test
    fun `given invalid id, when retrieved, then throw error`() {
        given()
            .`when`().get("/person/invalid")
            .then()
            .statusCode(500)
            //.body(containsString("name"))
    }
    @Test
    fun `given valid id, when retrieved, then have JSON`() {
        given()
          .`when`().get("/person/john")
          .then()
             .statusCode(200)
             .body(containsString("john"))
    }

}
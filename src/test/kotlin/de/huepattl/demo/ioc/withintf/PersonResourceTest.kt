package de.huepattl.demo.ioc.withintf

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test

/**
 * Direct test for [PersonResource] using its default dependency.
 * This demonstrates testing against a 'real' application bootstrapped by [QuarkusTest].
 * The test emits HTTP calls using REST Assured.
 */
@QuarkusTest
class PersonResourceTest {

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
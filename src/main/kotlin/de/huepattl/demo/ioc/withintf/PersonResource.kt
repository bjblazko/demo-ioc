package de.huepattl.demo.ioc.withintf

import jakarta.enterprise.context.RequestScoped
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@RequestScoped
@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
class PersonResource(val personService: PersonService) {

    @GET
    @Path("{id}")
    fun person(@PathParam("id") id: String): Response {
        val result = personService.getById(id)

        return if (result.isFailure) {
            Response.status(500, "Failed to return person: ${result.exceptionOrNull()}").build()
        } else {
            val person = result.getOrThrow()
            Response.ok(
                """
                {
                    "id": "${person.id}",
                    "name": "${person.name}",
                    "dateOfBirth": "${person.dateOfBirth}"
                }
            """.trimIndent()
            ).build()
        }
    }

}
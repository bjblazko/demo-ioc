package de.huepattl.demo.ioc.bystructure

import jakarta.enterprise.context.RequestScoped
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@RequestScoped
@Path("/person/structure")
@Produces(MediaType.APPLICATION_JSON)
class PersonResource(
    val personService: PersonService,
    val personRepository: PersonRepository
) {

    @GET
    @Path("{id}")
    fun person(@PathParam("id") id: String): Response {
        val repoResult = personRepository.getById(id)

        return if (repoResult.isFailure) {
            Response.status(500, "Failed to return person: ${repoResult.exceptionOrNull()}").build()
        } else {
            val personWithoutAge = repoResult.getOrThrow()
            val person = personService.calculateAge(personWithoutAge)

            Response.ok(
                """
                {
                    "id": "${person.id}",
                    "name": "${person.name}",
                    "dateOfBirth": "${person.dateOfBirth}",
                    "age": ${person.age}
                }
            """.trimIndent()
            ).build()
        }
    }

}
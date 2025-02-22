package com.haazer.school.controller


import com.haazer.school.dto.OwnerDTO
import com.haazer.school.entity.Owner
import com.haazer.school.service.OwnerService
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.net.URI


@Path("api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
class OwnerResource {

    @Inject
    lateinit var ownerService: OwnerService

    private val logger: Logger = LoggerFactory.getLogger(OwnerResource::class.java)


    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    fun login(
        @FormParam("username") username: String,
        @FormParam("password") password: String
    ): Response {
        logger.info("REST request to login user {},{} : ", username, password)
        val jwtResponse = ownerService.login(username, password)
        return Response.ok(jwtResponse).build()
    }

    @POST
    @Path("/owners/register")
    @Consumes(MediaType.APPLICATION_JSON)
    fun register(ownerDTO: OwnerDTO): Response {
        logger.info("REST request to register owner {} :", ownerDTO)
        if (ownerDTO.id != null) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity(mapOf("error" to "A new user cannot already have an ID"))
                .build()
        }

        val result = ownerService.registerUser(ownerDTO)
        return Response.created(URI.create("/api/owners/register/${result.id}"))
            .entity(result)
            .build()
    }

    @GET
    @Path("/owners")
    fun getAllOwners(): Response {
        logger.info("REST request to get All owners")
        val owners = ownerService.getAllOwners()
        return if (owners.isNotEmpty()) {
            Response.ok(owners).build()
        } else {
            Response.status(Response.Status.NO_CONTENT).build()
        }
    }

    @GET
    @Path("/owners/{id}")
    fun getOwnerById(@PathParam("id") id: Long): Response {
        logger.info("REST request to get owner with id {} :", id)
        val owner = ownerService.getOwnerById(id)
        return if (owner != null) {
            Response.ok(owner).build()
        } else {
            Response.status(Response.Status.NOT_FOUND).entity(mapOf("error" to "Owner not found")).build()
        }
    }

    @DELETE
    @Path("/owners/{id}")
    fun deleteOwner(@PathParam("id") id: Long): Response {
        logger.info("REST request to delete owner with id {} :", id)
        val deleted = ownerService.deleteOwner(id)
        return if (deleted) {
            Response.status(Response.Status.NO_CONTENT).build()
        } else {
            Response.status(Response.Status.NOT_FOUND).entity(mapOf("error" to "Owner not found")).build()
        }
    }

    @PUT
    @Path("/owners/{id}")
    fun updateOwner(@PathParam("id") id: Long, owner: OwnerDTO): Response {
        logger.info("REST request to update owner with id {}: {}", id, owner)
        try {
            val updatedOwner = ownerService.updateOwner(id, owner)
            return Response.ok(updatedOwner).build()
        } catch (e: Exception) {
            return Response.status(Response.Status.NOT_FOUND)
                .entity(mapOf("error" to e.message))
                .build()
        }
    }

}
package com.haazer.school.controller

import com.haazer.school.entity.Owner
import com.haazer.school.service.OwnerService
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/owners")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
class OwnerController{

    @Inject
    lateinit var ownerService: OwnerService

    @POST
    fun createOwner(owner: Owner): Response {
        ownerService.createOwner(owner)
        return Response.status(Response.Status.CREATED).entity(owner).build()
    }

    @GET
    fun getAllOwners(): List<Owner> {
        return ownerService.getAllOwners()
    }

    @GET
    @Path("/{id}")
    fun getOwnerById(@PathParam("id") id: Long): Response {
        val owner = ownerService.getOwnerById(id)
        return if (owner != null) {
            Response.ok(owner).build()
        } else {
            Response.status(Response.Status.NOT_FOUND).build()
        }
    }

    @DELETE
    @Path("/{id}")
    fun deleteOwner(@PathParam("id") id: Long): Response {
        val deleted = ownerService.deleteOwner(id)
        return if (deleted) {
            Response.status(Response.Status.NO_CONTENT).build()
        } else {
            Response.status(Response.Status.NOT_FOUND).build()
        }
    }
}

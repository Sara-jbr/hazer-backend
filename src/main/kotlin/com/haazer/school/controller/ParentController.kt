package com.haazer.school.controller

import com.haazer.school.entity.Parent
import com.haazer.school.repository.ParentRepository
import com.haazer.school.service.ParentService
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response


@Path("/parents")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
class ParentController  {

    @Inject
    lateinit var parentService: ParentService


    @POST
    fun createParent(parent: Parent): Response {
        parentService.createParent(parent)
        return Response.status(Response.Status.CREATED).entity(parent).build()
    }

    @GET
    fun getAllParents(): List<Parent> {
        return parentService.getAllParents()
    }

    @GET
    @Path("/{id}")
    fun getParentById(@PathParam("id") id: Long): Response {
        val parent = parentService.getParentById(id)
        return if (parent != null) {
            Response.ok(parent).build()
        } else {
            Response.status(Response.Status.NOT_FOUND).build()
        }
    }

    @DELETE
    @Path("/{id}")
    fun deleteParent(@PathParam("id") id: Long): Response {
        val deleted = parentService.deleteParent(id)
        return if (deleted) {
            Response.status(Response.Status.NO_CONTENT).build()
        } else {
            Response.status(Response.Status.NOT_FOUND).build()
        }
    }


}


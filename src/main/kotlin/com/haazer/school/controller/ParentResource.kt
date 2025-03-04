package com.haazer.school.controller

import com.haazer.school.dto.ParentDTO
import com.haazer.school.service.ParentService
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.net.URI


@Path("/api/parents")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
class ParentResource {

    @Inject
    lateinit var parentService: ParentService

    private val logger: Logger = LoggerFactory.getLogger(ParentResource::class.java)

    @POST
    @Path("/create")
    fun createParent(parent: ParentDTO): Response {
        logger.info("REST request to create a parent: {}", parent)

        val createdParent = parentService.createParent(parent)
        return Response.created(URI.create("/api/parents/${createdParent.id}"))
            .entity(createdParent)
            .build()
    }

    @GET
    @Path("/")
    fun getAllParents(): Response {
        logger.info("REST request to get all parents")
        val parents = parentService.getAllParents()
        return if (parents.isNotEmpty()) {
            Response.ok(parents).build()
        } else {
            Response.status(Response.Status.NO_CONTENT).build()
        }
    }

    @GET
    @Path("/{id}")
    fun getParentById(@PathParam("id") id: Long): Response {
        logger.info("REST request to get parent with id {} :", id)
        val parent = parentService.getParentById(id)
        return if (parent != null) {
            Response.ok(parent).build()
        } else {
            Response.status(Response.Status.NOT_FOUND)
                .entity(mapOf("error" to "Parent not found"))
                .build()
        }
    }

    @DELETE
    @Path("/{id}")
    fun deleteParent(@PathParam("id") id: Long): Response {
        logger.info("REST request to delete parent with id {} :", id)
        val deleted = parentService.deleteParent(id)
        return if (deleted) {
            Response.status(Response.Status.NO_CONTENT).build()
        } else {
            Response.status(Response.Status.NOT_FOUND)
                .entity(mapOf("error" to "Parent not found"))
                .build()
        }
    }

    @PUT
    @Path("/{id}")
    fun updateParent(@PathParam("id") id: Long, parent: ParentDTO): Response {
        logger.info("REST request to update parent with id {}: {}", id, parent)
        return try {
            val updated = parentService.updateParent(id, parent)
            Response.ok(updated).build()
        } catch (e: Exception) {
            Response.status(Response.Status.BAD_REQUEST)
                .entity(mapOf("error" to e.message))
                .build()
        }
    }
    @POST
    @Path("{parentId}/assign-student/{studentId}")
    fun assignStudentToParent(
        @PathParam("parentId") parentId: Long,
        @PathParam("studentId") studentId: Long

    ) {
        parentService.assignStudentToParent(studentId, parentId)
    }
}

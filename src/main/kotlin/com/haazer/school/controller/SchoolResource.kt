package com.haazer.school.controller

import com.haazer.school.dto.SchoolDTO
import com.haazer.school.entity.School
import com.haazer.school.service.SchoolService
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
class SchoolResource {

    @Inject
    lateinit var schoolService: SchoolService

    private val logger: Logger = LoggerFactory.getLogger(SchoolResource::class.java)

    @POST
    @Path("/schools/create/{ownerId}")
    fun createSchool(@PathParam("ownerId") ownerId: Long, school: SchoolDTO): Response {
        logger.info("REST request to create a school by ownerId {}, {} :", ownerId, school)

        val createdSchool = schoolService.createSchool(ownerId, school)
        return Response.created(URI.create("/api/schools/${createdSchool.id}"))
            .entity(createdSchool)
            .build()
    }


    @GET
    @Path("/schools")
    fun getAllSchools(): Response {
        logger.info("REST request to get all schools")
        val schools = schoolService.getAllSchools()
        return if (schools.isNotEmpty()) {
            Response.ok(schools).build()
        } else {
            Response.status(Response.Status.NO_CONTENT).build()
        }
    }


    @GET
    @Path("/schools/{id}")
    fun getSchoolById(@PathParam("id") id: Long): Response {
        logger.info("REST request to get school with id {} :", id)
        val school = schoolService.getSchoolById(id)
        return if (school != null) {
            Response.ok(school).build()
        } else {
            Response.status(Response.Status.NOT_FOUND)
                .entity(mapOf("error" to "School not found"))
                .build()
        }
    }

    @DELETE
    @Path("/schools/{id}")
    fun deleteSchool(@PathParam("id") id: Long): Response {
        logger.info("REST request to delete school with id {} :", id)
        val deleted = schoolService.deleteSchool(id)
        return if (deleted) {
            Response.status(Response.Status.NO_CONTENT).build()
        } else {
            Response.status(Response.Status.NOT_FOUND)
                .entity(mapOf("error" to "School not found"))
                .build()
        }
    }

        @PUT
        @Path("/schools/{id}")
        fun updateSchool(
            @PathParam("id") id: Long, school: SchoolDTO): Response {
            logger.info("REST request to update school with id {}: {}", id, school)
            return try {
                val updated = schoolService.updateSchool(id, school)
                Response.ok(updated).build()
            } catch (e: Exception) {
                Response.status(Response.Status.BAD_REQUEST)
                    .entity(mapOf("error" to e.message))
                    .build()
            }
        }
    }


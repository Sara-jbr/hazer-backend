package com.haazer.school.controller

import com.haazer.school.entity.School
import com.haazer.school.service.SchoolService
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response


@Path("/schools")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
class SchoolController {

    @Inject
    lateinit var schoolService: SchoolService

    @POST
    fun createSchool(school: School): Response {
        schoolService.createSchool(school)
        return Response.status(Response.Status.CREATED).entity(school).build()
    }

    @GET
    fun getAllSchools(): List<School> {
        return schoolService.getAllSchools()
    }

    @GET
    @Path("/{id}")
    fun getSchoolById(@PathParam("id") id: Long): Response {
        val school = schoolService.getSchoolById(id)
        return if (school != null) {
            Response.ok(school).build()
        } else {
            Response.status(Response.Status.NOT_FOUND).build()
        }
    }

    @DELETE
    @Path("/{id}")
    fun deleteSchool(@PathParam("id") id: Long): Response {
        val deleted = schoolService.deleteSchool(id)
        return if (deleted) {
            Response.status(Response.Status.NO_CONTENT).build()
        } else {
            Response.status(Response.Status.NOT_FOUND).build()
        }
    }
}

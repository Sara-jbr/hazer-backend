package com.haazer.school.controller

import com.haazer.school.dto.TeacherDTO
import com.haazer.school.service.TeacherService
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.net.URI

@Path("/api/teachers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
class TeacherResource {

    @Inject
    lateinit var teacherService: TeacherService

    private val logger: Logger = LoggerFactory.getLogger(TeacherResource::class.java)

    @POST
    fun createTeacher(teacher: TeacherDTO): Response {
        logger.info("REST request to create a teacher: {}", teacher)

        val createdTeacher = teacherService.createTeacher(teacher)
        return Response.created(URI.create("/api/teachers/${createdTeacher.id}"))
            .entity(createdTeacher)
            .build()
    }

    @GET
    fun getAllTeachers(): Response {
        logger.info("REST request to get all teachers")
        val teachers = teacherService.getAllTeachers()
        return if (teachers.isNotEmpty()) {
            Response.ok(teachers).build()
        } else {
            Response.status(Response.Status.NO_CONTENT).build()
        }
    }

    @GET
    @Path("/{id}")
    fun getTeacherById(@PathParam("id") id: Long): Response {
        logger.info("REST request to get teacher with id {} :", id)
        val teacher = teacherService.getTeacherById(id)
        return if (teacher != null) {
            Response.ok(teacher).build()
        } else {
            Response.status(Response.Status.NOT_FOUND)
                .entity(mapOf("error" to "Teacher not found"))
                .build()
        }
    }

    @DELETE
    @Path("/{id}")
    fun deleteTeacher(@PathParam("id") id: Long): Response {
        logger.info("REST request to delete teacher with id {} :", id)
        val deleted = teacherService.deleteTeacher(id)
        return if (deleted) {
            Response.status(Response.Status.NO_CONTENT).build()
        } else {
            Response.status(Response.Status.NOT_FOUND)
                .entity(mapOf("error" to "Teacher not found"))
                .build()
        }
    }

    @PUT
    @Path("/{id}")
    fun updateTeacher(@PathParam("id") id: Long, teacher: TeacherDTO): Response {
        logger.info("REST request to update teacher with id {}: {}", id, teacher)
        return try {
            val updated = teacherService.updateTeacher(id, teacher)
            Response.ok(updated).build()
        } catch (e: Exception) {
            Response.status(Response.Status.BAD_REQUEST)
                .entity(mapOf("error" to e.message))
                .build()
        }
    }
}

package com.haazer.school.controller

import com.haazer.school.dto.StudentDTO
import com.haazer.school.service.StudentService
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.net.URI

@Path("/api/students")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
class StudentResource {

    @Inject
    lateinit var studentService: StudentService

    private val logger: Logger = LoggerFactory.getLogger(StudentResource::class.java)

    @POST
    @Path("/create")
    fun createStudent(student: StudentDTO): Response {
        logger.info("REST request to create a student: {}", student)

        val createdStudent = studentService.createStudent(student)
        return Response.created(URI.create("/api/students/${createdStudent.id}"))
            .entity(createdStudent)
            .build()
    }

    @GET
    @Path("/")
    fun getAllStudents(): Response {
        logger.info("REST request to get all students")
        val students = studentService.getAllStudents()
        return if (students.isNotEmpty()) {
            Response.ok(students).build()
        } else {
            Response.status(Response.Status.NO_CONTENT).build()
        }
    }

    @GET
    @Path("/{id}")
    fun getStudentById(@PathParam("id") id: Long): Response {
        logger.info("REST request to get student with id {} :", id)
        val student = studentService.getStudentById(id)
        return if (student != null) {
            Response.ok(student).build()
        } else {
            Response.status(Response.Status.NOT_FOUND)
                .entity(mapOf("error" to "Student not found"))
                .build()
        }
    }

    @DELETE
    @Path("/{id}")
    fun deleteStudent(@PathParam("id") id: Long): Response {
        logger.info("REST request to delete student with id {} :", id)
        val deleted = studentService.deleteStudent(id)
        return if (deleted) {
            Response.status(Response.Status.NO_CONTENT).build()
        } else {
            Response.status(Response.Status.NOT_FOUND)
                .entity(mapOf("error" to "Student not found"))
                .build()
        }
    }

    @PUT
    @Path("/{id}")
    fun updateStudent(@PathParam("id") id: Long, student: StudentDTO): Response {
        logger.info("REST request to update student with id {}: {}", id, student)
        return try {
            val updated = studentService.updateStudent(id, student)
            Response.ok(updated).build()
        } catch (e: Exception) {
            Response.status(Response.Status.BAD_REQUEST)
                .entity(mapOf("error" to e.message))
                .build()
        }
    }

}

package com.haazer.school.controller

import com.haazer.school.entity.Student
import com.haazer.school.service.StudentService
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response


@Path("/students")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
class StudentController{

    @Inject
    lateinit var studentService: StudentService

    @POST
    fun createStudent(student: Student): Response {
        studentService.createStudent(student)
        return Response.status(Response.Status.CREATED).entity(student).build()
    }

    @GET
    fun getAllStudents(): List<Student> {
        return studentService.getAllStudents()
    }

    @GET
    @Path("/{id}")
    fun getStudentById(@PathParam("id") id: Long): Response {
        val student = studentService.getStudentById(id)
        return if (student != null) {
            Response.ok(student).build()
        } else {
            Response.status(Response.Status.NOT_FOUND).build()
        }
    }

    @DELETE
    @Path("/{id}")
    fun deleteStudent(@PathParam("id") id: Long): Response {
        val deleted = studentService.deleteStudent(id)
        return if (deleted) {
            Response.status(Response.Status.NO_CONTENT).build()
        } else {
            Response.status(Response.Status.NOT_FOUND).build()
        }
    }
}

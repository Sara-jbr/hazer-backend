package com.haazer.school.controller


import com.haazer.school.entity.Teacher
import com.haazer.school.service.TeacherService
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response


@Path("/teachers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
class TeacherController {

    @Inject
    lateinit var teacherService: TeacherService


    @POST
    fun createTeacher(teacher: Teacher): Response {
        teacherService.createTeacher(teacher)
        return Response.status(Response.Status.CREATED).entity(teacher).build()
    }

    @GET
    fun getAllTeachers(): List<Teacher> {
        return teacherService.getAllTeachers()
    }

    @GET
    @Path("/{id}")
    fun getTeacherById(@PathParam("id") id: Long): Response {
        val teacher = teacherService.getTeacherById(id)
        return if (teacher != null) {
            Response.ok(teacher).build()
        } else {
            Response.status(Response.Status.NOT_FOUND).build()
        }
    }

    @DELETE
    @Path("/{id}")
    fun deleteTeacher(@PathParam("id") id: Long): Response {
        val deleted = teacherService.deleteTeacher(id)
        return if (deleted) {
            Response.status(Response.Status.NO_CONTENT).build()
        } else {
            Response.status(Response.Status.NOT_FOUND).build()
        }
    }
}

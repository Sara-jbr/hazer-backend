package com.haazer.school.controller


import com.haazer.school.dto.ClassRoomDTO
import com.haazer.school.service.ClassRoomService
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.net.URI


@Path("/api/classrooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
class ClassRoomResource {

    @Inject
    lateinit var classRoomService: ClassRoomService

    private val logger: Logger = LoggerFactory.getLogger(ClassRoomResource::class.java)

    @POST
    @Path("/create")
    fun createClassRoom(classRoom: ClassRoomDTO): Response {
        logger.info("REST request to create a classroom {} :", classRoom)

        val createdClassRoom = classRoomService.createClassRoom(classRoom)
        return Response.created(URI.create("/api/classrooms/${createdClassRoom.id}"))
            .entity(createdClassRoom)
            .build()
    }

    @GET
    @Path("/")
    fun getAllClassRooms(): Response {
        logger.info("REST request to get all classrooms")
        val classrooms = classRoomService.getAllClassRooms()
        return if (classrooms.isNotEmpty()) {
            Response.ok(classrooms).build()
        } else {
            Response.status(Response.Status.NO_CONTENT).build()
        }
    }

    @GET
    @Path("/{id}")
    fun getClassRoomById(@PathParam("id") id: Long): Response {
        logger.info("REST request to get classroom with id {} :", id)
        val classRoom = classRoomService.getClassRoomById(id)
        return if (classRoom != null) {
            Response.ok(classRoom).build()
        } else {
            Response.status(Response.Status.NOT_FOUND)
                .entity(mapOf("error" to "Classroom not found"))
                .build()
        }
    }

    @DELETE
    @Path("/{id}")
    fun deleteClassRoom(@PathParam("id") id: Long): Response {
        logger.info("REST request to delete classroom with id {} :", id)
        val deleted = classRoomService.deleteClassRoom(id)
        return if (deleted) {
            Response.status(Response.Status.NO_CONTENT).build()
        } else {
            Response.status(Response.Status.NOT_FOUND)
                .entity(mapOf("error" to "Classroom not found"))
                .build()
        }
    }

    @PUT
    @Path("/{id}")
    fun updateClassRoom(@PathParam("id") id: Long, classRoom: ClassRoomDTO): Response {
        logger.info("REST request to update classroom with id {}: {}", id, classRoom)
        return try {
            val updated = classRoomService.updateClassRoom(id, classRoom)
            Response.ok(updated).build()
        } catch (e: Exception) {
            Response.status(Response.Status.BAD_REQUEST)
                .entity(mapOf("error" to e.message))
                .build()
        }
    }

    @POST
    @Path("{classroomId}/assign-student/{studentId}")
    fun assignStudentToClassroom(
        @PathParam("classroomId")classroomId: Long,
        @PathParam("studentId") studentId: Long

    ) {
        classRoomService.assignStudentToClassroom(classroomId, studentId)
    }

    @POST
    @Path("{classroomId}/assign-teacher/{teacherId}")
    fun assignTeacherToClassroom(
        @PathParam("classroomId")classroomId: Long,
        @PathParam("teacherId") teacherId: Long

    ) {
        classRoomService.assignTeacherToClassroom(classroomId, teacherId)
    }
}


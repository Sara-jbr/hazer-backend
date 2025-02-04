package com.haazer.school.controller

import com.haazer.school.entity.ClassRoom
import com.haazer.school.service.ClassRoomService
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/classrooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
class ClassRoomController  {

    @Inject
    lateinit var classRoomService: ClassRoomService

    @POST
    fun createClassroom(classRoom: ClassRoom): Response {
        classRoomService.createClassRoom(classRoom)
        return Response.status(Response.Status.CREATED).entity(classRoom).build()
    }

    @GET
    fun getAllClassRooms(): List<ClassRoom> {
        return classRoomService.getAllClassRooms()
    }

    @GET
    @Path("/{id}")
    fun getClassRoomById(@PathParam("id") id: Long): Response {
        val classRoom = classRoomService.getClassRoomById(id)
        return if (classRoom != null) {
            Response.ok(classRoom).build()
        } else {
            Response.status(Response.Status.NOT_FOUND).build()
        }
    }

    @DELETE
    @Path("/{id}")
    fun deleteClassRoom(@PathParam("id") id: Long): Response {
        val deleted = classRoomService.deleteClassRoom(id)
        return if (deleted) {
            Response.status(Response.Status.NO_CONTENT).build()
        } else {
            Response.status(Response.Status.NOT_FOUND).build()
        }
    }
}

package com.haazer.school.controller

import com.haazer.school.entity.User
import com.haazer.school.service.UserService
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response


@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
class UserController {

    @Inject
    lateinit var userService: UserService

    @POST
    fun createUser(user: User): Response {
        userService.createUser(user)
        return Response.status(Response.Status.CREATED).entity(user).build()
    }

    @GET
    fun getAllUsers(): List<User> {
        return userService.getAllUsers()
    }

    @GET
    @Path("/{id}")
    fun getUserById(@PathParam("id") id: Long): Response {
        val user = userService.getUserById(id)
        return if (user != null) {
            Response.ok(user).build()
        } else {
            Response.status(Response.Status.NOT_FOUND).build()
        }
    }

    @DELETE
    @Path("/{id}")
    fun deleteUser(@PathParam("id") id: Long): Response {
        val deleted = userService.deleteUser(id)
        return if (deleted) {
            Response.status(Response.Status.NO_CONTENT).build()
        } else {
            Response.status(Response.Status.NOT_FOUND).build()
        }
    }



}

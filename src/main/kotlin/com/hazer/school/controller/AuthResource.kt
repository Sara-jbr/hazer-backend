package com.hazer.school.controller

import io.quarkus.security.Authenticated
import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.core.Response
import org.eclipse.microprofile.jwt.JsonWebToken


@Path("/login")
class LoginResource {

    @Inject
    lateinit var jwt: JsonWebToken

    @GET
    @Authenticated
    fun login(): Response {
        // This is a simple example. You can also include user data or token info.
        val username = jwt.name
        return Response.ok("Hello, $username!").build()
    }
}
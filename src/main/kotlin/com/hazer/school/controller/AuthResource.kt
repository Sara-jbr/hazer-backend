package com.hazer.school.controller


import com.hazer.school.dto.UserDTO
import com.hazer.school.service.UserService
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse


@ApplicationScoped
@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)

class AuthResource {

    @Inject
    lateinit var userService: UserService

    @ConfigProperty(name = "quarkus.oidc.auth-server-url")
    lateinit var keycloakUrl: String

    private val logger: Logger = LoggerFactory.getLogger(UserService::class.java)
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED) // Change this to support form data
    fun login(@FormParam("username") username: String, @FormParam("password") password: String): Response {
        val requestBody = "client_id=hazer-app&client_secret=7xbrkI3bu9cyV4nDjlHKKezYUMK0aQUp&grant_type=password&username=$username&password=$password"

        val request = HttpRequest.newBuilder()
            .uri(URI.create("$keycloakUrl/protocol/openid-connect/token"))
            .header("Content-Type", "application/x-www-form-urlencoded")
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build()

        val client = HttpClient.newHttpClient()
        val response = client.send(request, HttpResponse.BodyHandlers.ofString())
        logger.error("response: ${response.body()}")
        return if (response.statusCode() == 200) {
            // Successful login response
            Response.ok(response.body()).build()
        } else {
            // Handle error response (non-200 status codes)
            Response.status(response.statusCode())
                .entity("Error: ${response.body()}")
                .build()
        }
    }

    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    fun signUp(user: UserDTO): Response {
       logger.info("user {}", user)
        return userService.registerUser(user)
    }
}
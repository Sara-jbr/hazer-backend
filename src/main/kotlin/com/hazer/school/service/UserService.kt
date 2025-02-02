package com.hazer.school.service

import com.hazer.school.dto.UserDTO
import io.vertx.core.json.JsonObject
import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.core.Response
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse


@ApplicationScoped
class UserService {

    private val logger: Logger = LoggerFactory.getLogger(UserService::class.java)

    @ConfigProperty(name = "quarkus.oidc.auth-server-url")
    lateinit var keycloakUrl: String

    @ConfigProperty(name = "quarkus.oidc.client-id")
    lateinit var clientId: String

    @ConfigProperty(name = "quarkus.oidc.credentials.secret")
    lateinit var clientSecret: String

    fun registerUser(user: UserDTO): Response {
        val userJson = JsonObject()
            .put("username", user.username)
            .put("firstName", user.firstName)
            .put("lastName", user.lastName)
            .put("email", user.email)
            .put("enabled", true)
            .put("emailVerified", true)
            .put("credentials", listOf(
                JsonObject()
                    .put("type", "password")
                    .put("value", user.password)
                    .put("temporary", false)
            ))

        val request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8080/admin/realms/hazer-realm/users"))
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer ${getAdminToken()}")
            .POST(HttpRequest.BodyPublishers.ofString(userJson.encode()))
            .build()

        val client = HttpClient.newHttpClient()
        val response = client.send(request, HttpResponse.BodyHandlers.ofString())
        logger.info("response {}", response.body())
        return if (response.statusCode() == 201) {
            Response.ok(mapOf("message" to "User registered successfully")).build()
        } else {
            logger.error("Failed to register user: ${response.body()}")
            Response.status(response.statusCode()).entity(response.body()).build()
        }
    }

    private fun getAdminToken(): String {
        val requestBody = "client_id=admin-cli&username=admin&password=admin&grant_type=password"
        val request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8080/realms/master/protocol/openid-connect/token"))
            .header("Content-Type", "application/x-www-form-urlencoded")
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build()

        val client = HttpClient.newHttpClient()
        val response = client.send(request, HttpResponse.BodyHandlers.ofString())
        val jsonResponse = JsonObject(response.body())
        logger.info("jsonResponse {}", jsonResponse)

        return jsonResponse.getString("access_token")
    }
}
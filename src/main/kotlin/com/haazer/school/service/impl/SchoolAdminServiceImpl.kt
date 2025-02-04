package com.haazer.school.service.impl

import com.haazer.school.dto.SchoolAdminDTO
import com.haazer.school.entity.SchoolAdmin
import com.haazer.school.repository.SchoolAdminRepository
import com.haazer.school.service.SchoolAdminService
import io.vertx.core.json.JsonObject
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import jakarta.ws.rs.core.Response
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.LocalDateTime
import java.time.ZonedDateTime

@ApplicationScoped
class SchoolAdminServiceImpl : SchoolAdminService {

    private val logger: Logger = LoggerFactory.getLogger(SchoolAdminService::class.java)

    @ConfigProperty(name = "quarkus.oidc.auth-server-url")
    lateinit var keycloakUrl: String

    @ConfigProperty(name = "quarkus.oidc.client-id")
    lateinit var clientId: String

    @ConfigProperty(name = "quarkus.oidc.credentials.secret")
    lateinit var clientSecret: String

    @Inject
    lateinit var schoolAdminRepository: SchoolAdminRepository

    @Transactional
    override fun createSchoolAdmin(schoolAdmin: SchoolAdmin) {
        schoolAdminRepository.persist(schoolAdmin)
    }

    override fun getAllSchoolAdmins(): List<SchoolAdmin> {
        return schoolAdminRepository.listAll()
    }

    override fun getSchoolAdminById(id: Long): SchoolAdmin? {
        return schoolAdminRepository.findById(id)
    }

    @Transactional
    override fun deleteSchoolAdmin(id: Long): Boolean {
        val schoolAdmin = schoolAdminRepository.findById(id)
        return if (schoolAdmin != null) {
            schoolAdminRepository.deleteById(id)
            true
        } else {
            false
        }
    }

    @Transactional
    override fun registerUser(schoolAdmin: SchoolAdminDTO): Response {
        val userJson = JsonObject()
            .put("username", schoolAdmin.username)
            .put("firstName", schoolAdmin.firstName)
            .put("lastName", schoolAdmin.lastName)
            .put("email", schoolAdmin.email)
            .put("enabled", true)
            .put("emailVerified", true)
            .put(
                "credentials", listOf(
                    JsonObject()
                        .put("type", "password")
                        .put("value", schoolAdmin.password)
                        .put("temporary", false)
                )
            )

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

            val schoolAdmin = SchoolAdmin(
                username = schoolAdmin.username,
                firstName = schoolAdmin.firstName,
                lastName = schoolAdmin.lastName,
                email = schoolAdmin.email,
                password = schoolAdmin.password,
                createdAt = ZonedDateTime.now(), // If needed
                owner = null  // Adjust this if an owner is required
            )

            schoolAdminRepository.persist(schoolAdmin)

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


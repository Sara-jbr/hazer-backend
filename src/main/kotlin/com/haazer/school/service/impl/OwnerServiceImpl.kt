package com.haazer.school.service.impl

import com.haazer.school.config.KeycloakConfig
import com.haazer.school.dto.OwnerDTO
import com.haazer.school.entity.Owner
import com.haazer.school.repository.OwnerRepository
import com.haazer.school.service.OwnerService
import com.haazer.school.util.CommonUtil
import io.vertx.core.json.JsonObject
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import jakarta.ws.rs.core.Response
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.ZonedDateTime

@ApplicationScoped
class OwnerServiceImpl : OwnerService {

    private val logger: Logger = LoggerFactory.getLogger(OwnerServiceImpl::class.java)

    @Inject
    lateinit var ownerRepository: OwnerRepository

    @Inject
    lateinit var keycloakConfig: KeycloakConfig


    @Transactional
    override fun registerUser(ownerDTO: OwnerDTO): Owner {
        try {

            val existingOwner = ownerDTO.email?.let { ownerRepository.findByEmail(it) }
            if (existingOwner != null) {
                throw RuntimeException("User with email ${ownerDTO.email} already exists")
            }


            val userJson = JsonObject()
                .put("username", ownerDTO.userName)
                .put("firstName", ownerDTO.firstName)
                .put("lastName", ownerDTO.lastName)
                .put("email", ownerDTO.email)
                .put("enabled", true)
                .put("emailVerified", true)
                .put(
                    "credentials", listOf(
                        JsonObject()
                            .put("type", "password")
                            .put("value", ownerDTO.password)
                            .put("temporary", false)
                    )
                )


            val adminToken = getAdminToken()
            if (adminToken.isEmpty()) {
                throw RuntimeException("Failed to retrieve admin token from Keycloak")
            }


            val request = HttpRequest.newBuilder()
                .uri(URI.create(keycloakConfig.keycloakAdminUrl))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer $adminToken")
                .POST(HttpRequest.BodyPublishers.ofString(userJson.encode()))
                .build()

            val client = HttpClient.newHttpClient()
            val response = client.send(request, HttpResponse.BodyHandlers.ofString())

            logger.info("Keycloak registration response: ${response.statusCode()} - ${response.body()}")


            if (response.statusCode() == 201) {

                val owner = Owner(
                    userName = ownerDTO.userName,
                    firstName = ownerDTO.firstName,
                    lastName = ownerDTO.lastName,
                    email = ownerDTO.email,
                    password = ownerDTO.password,
                    createdAt = CommonUtil.gregorianToJalali(ZonedDateTime.now()),
                    schools = mutableListOf(),
                    modifiedAt = null,
                )

                ownerRepository.persist(owner)
                ownerRepository.flush() // Ensure it's written to the database

                if (owner.id == null) {
                    logger.error("Owner ID is null after persisting")
                    throw RuntimeException("Failed to generate ID")
                }

                return owner
            } else if (response.statusCode() == 409) {
                throw RuntimeException("User already exists in Keycloak")
            } else {
                throw RuntimeException("Failed to register user in Keycloak: ${response.body()}")
            }

        } catch (ex: Exception) {
            logger.error("Error during user registration: ${ex.message}", ex)
            throw RuntimeException("Registration failed: ${ex.message}")
        }
    }

    private fun getAdminToken(): String {
        val requestBody = "client_id=" + keycloakConfig.adminClientId + "&" + "username=" +
                keycloakConfig.adminUsername + "&" + "password=" + keycloakConfig.adminPassword + "&" + "grant_type=password"
        val request = HttpRequest.newBuilder()
            .uri(URI.create(keycloakConfig.tokenPathMaster))
            .header("Content-Type", "application/x-www-form-urlencoded")
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build()

        val client = HttpClient.newHttpClient()
        val response = client.send(request, HttpResponse.BodyHandlers.ofString())
        val jsonResponse = JsonObject(response.body())
        logger.info("jsonResponse {}", jsonResponse)

        return jsonResponse.getString("access_token")
    }


    @Transactional
    override fun login(username: String, password: String): Response {
        val existingUser = ownerRepository.findByEmailOrUsername(username)
            ?: return Response.status(Response.Status.UNAUTHORIZED)
                .entity("Invalid username or email")
                .build()

        // If user doesn't exist, return an error response
        if (existingUser.password != password) {
            return Response.status(Response.Status.UNAUTHORIZED)
                .entity("Incorrect password")
                .build()
        }
        val requestBody =
            "client_id=" + keycloakConfig.clientId + "&" + "client_secret=" + keycloakConfig.clientSecret + "&" + "grant_type=password&" + "username=" + username + "&password=" + password

        val request = HttpRequest.newBuilder()
            .uri(URI.create(keycloakConfig.tokenPath))
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


    override fun getAllOwners(): List<Owner> = ownerRepository.listAll()

    override fun getOwnerById(id: Long): Owner? = ownerRepository.findById(id)

    @Transactional
    override fun deleteOwner(id: Long): Boolean {
        val owner = ownerRepository.findById(id)
        return if (owner != null) {
            ownerRepository.deleteById(id)
            true
        } else {
            false
        }
    }

    @Transactional
    override fun updateOwner(id: Long, ownerDTO: OwnerDTO): Owner {
        val existingOwner = ownerRepository.findById(id)
            ?: throw Exception("Owner not found with ID: $id")

        existingOwner.apply {
            userName = ownerDTO.userName
            firstName = ownerDTO.firstName
            lastName = ownerDTO.lastName
            email = ownerDTO.email
            password = ownerDTO.password
            createdAt = createdAt
            modifiedAt = CommonUtil.gregorianToJalali(ZonedDateTime.now())
        }

        ownerRepository.persist(existingOwner)
        return existingOwner
    }

}

package com.hazer.school.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty


data class UserDTO @JsonCreator constructor(
    @JsonProperty("firstName") val firstName: String?,
    @JsonProperty("lastName") val lastName: String?,
    @JsonProperty("username") val username: String?,
    @JsonProperty("email") val email: String?,
    @JsonProperty("password") val password: String?
)
package com.haazer.school.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.ZonedDateTime

data class OwnerDTO @JsonCreator constructor(

    @JsonProperty("id") val id: Long? = null,

    @JsonProperty("firstName") val firstName: String?,

    @JsonProperty("lastName") val lastName: String?,

    @JsonProperty("userName") val userName: String?,

    @JsonProperty("email") val email: String?,

    @JsonProperty("password") val password: String?,

    @JsonProperty("createdDate") var createdAt: ZonedDateTime?,

    @JsonProperty("modifiedAt") var modifiedAt: ZonedDateTime?

    )
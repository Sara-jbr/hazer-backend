package com.haazer.school.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.haazer.school.entity.Student
import jakarta.persistence.*
import java.time.ZonedDateTime

class ParentDTO @JsonCreator constructor(

    @JsonProperty("id") val id: Long? = null,

    @JsonProperty("firstName") var firstName: String?,

    @JsonProperty("lastName") var lastName: String?,

    @JsonProperty("email") var email: String?,

    @JsonProperty("address") val address: String,

    @JsonProperty("phoneNumber") var phoneNumber: String,

    @JsonProperty("mobileNumber") var mobileNumber: String,

//    @JsonProperty("students") var students: MutableList<Student> = mutableListOf(),

    @JsonProperty("createdAt") var createdAt: ZonedDateTime?,

    @JsonProperty("modifiedAt") var modifiedAt: ZonedDateTime?
)
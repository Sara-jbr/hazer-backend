package com.haazer.school.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.haazer.school.entity.ClassRoom
import com.haazer.school.entity.Student
import jakarta.persistence.*
import java.time.ZonedDateTime

class TeacherDTO(

    @JsonProperty("id") val id: Long? = null,

    @JsonProperty("firstName") var firstName: String?,

    @JsonProperty("lastName") var lastName: String?,

    @JsonProperty("email") var email: String?,

    @JsonProperty("field") var field: String?,

//    @JsonProperty("classRooms") var classRooms: MutableList<ClassRoom> = mutableListOf(),

    @JsonProperty("createdAt") var createdAt: ZonedDateTime?,

    @JsonProperty("modifiedAt") var modifiedAt: ZonedDateTime?


    )
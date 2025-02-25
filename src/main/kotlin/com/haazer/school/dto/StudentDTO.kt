package com.haazer.school.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.haazer.school.entity.ClassRoom
import com.haazer.school.entity.Parent
import com.haazer.school.entity.Teacher
import com.haazer.school.entity.enumeration.GradeLevel
import java.time.ZonedDateTime

class StudentDTO(

    @JsonProperty("id") val id: Long? = null,

    @JsonProperty("firstName") var firstName: String?,

    @JsonProperty("lastName") var lastName: String?,

    @JsonProperty("email") var email: String?,

    @JsonProperty("studentNo") var studentNo: String,

    @JsonProperty("classRooms") var classRooms: MutableList<ClassRoom> = mutableListOf(),

    @JsonProperty("teachers") var teachers: MutableList<Teacher> = mutableListOf(),

    @JsonProperty("grade") var grade: GradeLevel,

    @JsonProperty("parents") var parents: MutableList<Parent> = mutableListOf(),

    @JsonProperty("createdAt") var createdAt: ZonedDateTime?,

    @JsonProperty("modifiedAt") var modifiedAt: ZonedDateTime?
)
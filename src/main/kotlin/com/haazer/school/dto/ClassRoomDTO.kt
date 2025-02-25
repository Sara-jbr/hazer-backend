package com.haazer.school.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.haazer.school.entity.Schedule
import com.haazer.school.entity.Student
import com.haazer.school.entity.Teacher
import jakarta.persistence.*
import java.time.ZonedDateTime

open class ClassRoomDTO(

    @JsonProperty("id") val id: Long? = null,

    @JsonProperty("className") var className: String,

    @JsonProperty("teacher") var teacher: Teacher? = null,

    @JsonProperty("students") var students: MutableList<Student> = mutableListOf(),

    @JsonProperty("createdAt") var createdAt: ZonedDateTime?,

    @JsonProperty("modifiedAt") var modifiedAt: ZonedDateTime?
)
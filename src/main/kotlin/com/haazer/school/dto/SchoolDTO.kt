package com.haazer.school.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.fasterxml.jackson.annotation.JsonProperty
import com.haazer.school.entity.Owner
import com.haazer.school.entity.Schedule
import com.haazer.school.entity.enumeration.GradeLevel
import com.haazer.school.entity.enumeration.SchoolType
import jakarta.persistence.*
import java.time.ZonedDateTime

data class SchoolDTO(

    @JsonProperty("id") val id: Long? = null,

    @JsonProperty("schoolName") var schoolName: String,

    @JsonProperty("address") var address: String,
    @JsonProperty("contactNumber") var contactNumber: String,

    @JsonProperty("schoolType") var schoolType: SchoolType,

    @JsonProperty("gradeLevel") var gradeLevel: GradeLevel,

    @JsonProperty("schedules") var schedules: MutableList<Schedule> = mutableListOf(),

    @JsonIgnore
    @JsonProperty("owner") var owner: Owner? = null,

    @JsonProperty("createdAt") var createdAt: ZonedDateTime?,

    @JsonProperty("modifiedAt") var modifiedAt: ZonedDateTime?

)


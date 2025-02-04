package com.haazer.school.dto

import com.haazer.school.entity.Student
import com.haazer.school.entity.User

open class ParentDTO(

    open val id: Long? = null,
    open val createdAt: String,
    open val user: User,
    open val address: String,
    open val phoneNumber: String,
    open val mobileNumber: String,
    open val student: List<Student>
)
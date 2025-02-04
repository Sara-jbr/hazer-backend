package com.haazer.school.dto

import com.haazer.school.entity.ClassRoom
import com.haazer.school.entity.Student
import com.haazer.school.entity.User

open class TeacherDTO(

    open val id: Long? = null,
    open val createdAt: String,
    open val user: User,
    open val classroom: List<ClassRoom>,
    open val student: List<Student>,
    open val field: String
)
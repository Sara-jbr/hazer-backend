package com.haazer.school.dto

import com.haazer.school.entity.ClassRoom
import com.haazer.school.entity.Parent
import com.haazer.school.entity.Teacher
import com.haazer.school.entity.User
import com.haazer.school.entity.enumeration.SchoolGrade

open class StudentDTO(

    open val id: Long? = null,
    open val createdAt: String,
    open val user: User,
    open var classroom: List<ClassRoom>,
    open val teacher: List<Teacher>,
    open var grade: SchoolGrade,
    open val parent: List<Parent>,
    open val studentNo: String
)
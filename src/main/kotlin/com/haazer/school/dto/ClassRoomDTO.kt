package com.haazer.school.dto

import com.haazer.school.entity.Student
import com.haazer.school.entity.Teacher

open class ClassRoomDTO(

    open val id: Long? = null,
    open val name: String,
    open val teacher: Teacher,
    open val student: List<Student>
)
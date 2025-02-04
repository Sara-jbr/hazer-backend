package com.haazer.school.dto

import com.haazer.school.entity.School
import com.haazer.school.entity.User

open class OwnerDTO(

    open val id: Long? = null,
    open val createdAt: String = "",
    open val user: User = User(),
    open val school: List<School> = listOf()
)
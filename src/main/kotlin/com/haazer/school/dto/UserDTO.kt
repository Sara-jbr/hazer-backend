package com.haazer.school.dto


open class UserDTO (

    open val id: Long? = null,
    open val firstName: String,
    open val lastName: String,
    open val email: String,
    open val birthDate: String,
    open val imageURL: String,
    open val socialNumber: String,
    open val createAt: String
)
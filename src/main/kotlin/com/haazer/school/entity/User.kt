package com.haazer.school.entity

import jakarta.persistence.*

@Entity
@Table(name = "user")
open class User(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long? = null,

    open val firstName: String,

    open val lastName: String,

    open val email: String,

    open val birthDate: String,

    open val imageURL: String,

    open val socialNumber: String,

    open val createAt: String


) {
    constructor() : this(
        firstName = "",
        lastName = "",
        email = "",
        birthDate = "",
        imageURL = "",
        socialNumber = "",
        createAt = ""
    )
}
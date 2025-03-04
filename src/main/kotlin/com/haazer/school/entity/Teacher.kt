package com.haazer.school.entity

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*

@Entity
@Table(name = "teacher")
open class Teacher(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long? = null,

    open var firstName: String?,

    open var lastName: String?,

    @JsonProperty("email")
    @Column(unique = true, nullable = false)
    open var email: String?,

    open var field: String?,

    open var createdAt: String?,

    open var modifiedAt: String?,


    ) {
    constructor() : this(
        firstName = "",
        lastName = "",
        email = "",
        field = "",
        createdAt = null,
        modifiedAt = null

    )

    override fun toString(): String {
        return "Teacher(id=$id, firstName=$firstName, lastName=$lastName, email=$email, field='$field', createdAt=$createdAt, modifiedAt=$modifiedAt)"
    }

}

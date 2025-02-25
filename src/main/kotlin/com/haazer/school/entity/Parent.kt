package com.haazer.school.entity

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*

@Entity
@Table(name = "parent")
open class Parent(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long? = null,

    open var firstName: String?,

    open var lastName: String?,

    @JsonProperty("email")
    @Column(unique = true, nullable = false)
    open var email: String?,

    open var address: String,

    open var phoneNumber: String,

    open var mobileNumber: String,

    @ManyToMany(mappedBy = "parents", cascade = [CascadeType.ALL])
    open var students: MutableList<Student> = mutableListOf(),

    open var createdAt: String?,

    open var modifiedAt: String?,

    ) {
    constructor() : this(
        firstName = "",
        lastName = "",
        email = "",
        address = "",
        phoneNumber = "",
        mobileNumber = "",
        students = mutableListOf(),
        createdAt = null,
        modifiedAt = null
    )
}
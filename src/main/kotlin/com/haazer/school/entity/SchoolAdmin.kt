package com.haazer.school.entity

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
@Table(name = "school_admin")
open class SchoolAdmin @JsonCreator constructor(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long? = null,

    @JsonProperty("firstName") open val firstName: String?,
    @JsonProperty("lastName") open val lastName: String?,
    @JsonProperty("username") open val username: String?,
    @JsonProperty("email") open val email: String?,
    @JsonProperty("password") open val password: String?,

    open val createdAt: ZonedDateTime? = null,

    @ManyToOne
    @JoinColumn(name = "owner_id")
    open val owner: Owner? = null

) {
    // No-argument constructor for Hibernate and Jackson deserialization
    constructor() : this(
        id = null,
        firstName = null,
        lastName = null,
        username = null,
        email = null,
        password = null,
        createdAt = null,
        owner = null
    )

    // Secondary constructor with default values
    constructor(
        firstName: String,
        lastName: String,
        username: String,
        email: String,
        password: String,
        createdAt: ZonedDateTime? = ZonedDateTime.now(),
        owner: Owner? = null
    ) : this(
        id = null, // The ID is auto-generated, so we leave it as null
        firstName = firstName,
        lastName = lastName,
        username = username,
        email = email,
        password = password,
        createdAt = createdAt,
        owner = owner
    )
}


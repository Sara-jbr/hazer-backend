package com.haazer.school.entity

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*

@Entity
@Table(name = "parent")
open class Parent @JsonCreator constructor(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id") open val id: Long? = null,

    @JsonProperty("firstName") open var firstName: String?,

    @JsonProperty("lastName") open var lastName: String?,

    @JsonProperty("email")
    @Column(unique = true, nullable = false)
    open var email: String?,

    @JsonProperty("address") open var address: String,

    @JsonProperty("phoneNumber") open var phoneNumber: String,

    @JsonProperty("mobileNumber") open var mobileNumber: String,

    @JsonProperty("createdAt") open var createdAt: String?,

    @JsonProperty("modifiedAt") open var modifiedAt: String?
) {
    // No-argument constructor for Hibernate and Jackson deserialization
    constructor() : this(
        firstName = null,
        lastName = null,
        email = null,
        address = "",
        phoneNumber = "",
        mobileNumber = "",
        createdAt = null,
        modifiedAt = null
    )

    // Secondary constructor for use when all fields are provided
    constructor(
        firstName: String?,
        lastName: String?,
        email: String?,
        address: String,
        phoneNumber: String,
        mobileNumber: String,
        createdAt: String?, // Default to current time if not provided
        modifiedAt: String?  // Default to current time if not provided
    ) : this(
        id = null,
        firstName = firstName,
        lastName = lastName,
        email = email,
        address = address,
        phoneNumber = phoneNumber,
        mobileNumber = mobileNumber,
        createdAt = createdAt,
        modifiedAt = modifiedAt
    )
}

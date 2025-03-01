package com.haazer.school.entity

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import java.time.ZonedDateTime


@Entity
@Table(name = "owner")
open class Owner @JsonCreator constructor(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id") open val id: Long? = null,

    @JsonProperty("firstName") open var firstName: String?,

    @JsonProperty("lastName") open var lastName: String?,

    @JsonProperty("userName")
    @Column(unique = true, nullable = false)
    open var userName: String?,

    @JsonProperty("email")
    @Column(unique = true, nullable = false)
    open var email: String?,

    @JsonProperty("password") open var password: String?,

    @JsonProperty("createdAt") open var createdAt: String?,

    @JsonProperty("modifiedAt") open var modifiedAt: String?,

    @OneToMany(mappedBy = "owner", cascade = [CascadeType.ALL], orphanRemoval = true,fetch = FetchType.EAGER)
    @JsonProperty("schools") open val schools: MutableList<School> = mutableListOf()

) {

    // No-argument constructor for Hibernate and Jackson deserialization
    constructor() : this(
        id = null,
        firstName = null,
        lastName = null,
        userName = null,
        email = null,
        password = null,
        createdAt = null,
        modifiedAt = null,
        schools = mutableListOf()
    )

    // Secondary constructor with default values
    constructor(
        firstName: String,
        lastName: String,
        userName: String,
        email: String,
        password: String,
        createdAt: String,  // Default to current time if not provided
        modifiedAt: String, // Default to current time if not provided
        schools: List<School>? = null
    ) : this(
        id = null,
        firstName = firstName,
        lastName = lastName,
        userName = userName,
        email = email,
        password = password,
        createdAt = createdAt,
        modifiedAt = modifiedAt,
        schools = schools?.toMutableList() ?: mutableListOf()
    )

    fun addSchool(school: School) {
        schools.add(school)
        school.owner = this
    }

    override fun toString(): String {
        return "Owner(id=$id, firstName='$firstName', lastName='$lastName', " +
                "userName='$userName', email='$email', password='$password', schools=${schools.size})"
    }
}

package com.haazer.school.entity

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.haazer.school.entity.enumeration.GradeLevel
import jakarta.persistence.*

@Entity
@Table(name = "student")
open class Student @JsonCreator constructor(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    open val id: Long? = null,

    @JsonProperty("firstName")
    open var firstName: String?,

    @JsonProperty("lastName")
    open var lastName: String?,

    @JsonProperty("studentNo")
    open var studentNo: String,

    @JsonProperty("email")
    @Column(unique = true, nullable = false)
    open var email: String?,

    @JsonProperty("grade")
    @Enumerated(EnumType.STRING)
    open var grade: GradeLevel,

    @ManyToMany(mappedBy = "students")
    open var parents: MutableList<Parent> = mutableListOf(),

    @ManyToMany(mappedBy = "students")
    open var classrooms: MutableList<ClassRoom> = mutableListOf(),

    @JsonProperty("createdAt")
    open var createdAt: String?,

    @JsonProperty("modifiedAt")
    open var modifiedAt: String?

) {
    constructor() : this(
        firstName = "",
        lastName = "",
        studentNo = "",
        email = "",
        grade = GradeLevel.ابتدایی,
        parents = mutableListOf(),
        createdAt = null,
        modifiedAt = null
    )

    constructor(
        firstName: String,
        lastName: String,
        studentNo: String,
        email: String,
        grade: String, // This is a String that will be converted to GradeLevel enum
        parents: List<Parent>? = null,
        createdAt: String,  // Default to current time if not provided
        modifiedAt: String // Default to current time if not provided
    ) : this(
        id = null,
        firstName = firstName,
        lastName = lastName,
        studentNo = studentNo,
        email = email,
        grade = GradeLevel.valueOf(grade),  // Convert String to enum here
        parents = parents?.toMutableList() ?: mutableListOf(),
        createdAt = createdAt,
        modifiedAt = modifiedAt
    )

    override fun toString(): String {
        return "Student(id=$id, firstName=$firstName, lastName=$lastName, studentNo='$studentNo', email=$email, grade=$grade, parents=$parents, classrooms=$classrooms, createdAt=$createdAt, modifiedAt=$modifiedAt)"
    }

}

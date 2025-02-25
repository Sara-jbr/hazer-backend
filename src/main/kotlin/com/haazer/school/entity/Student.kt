package com.haazer.school.entity

import com.fasterxml.jackson.annotation.JsonProperty
import com.haazer.school.entity.enumeration.GradeLevel
import jakarta.persistence.*

@Entity
@Table(name = "student")
open class Student(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long? = null,

    open var firstName: String?,

    open var lastName: String?,

    @JsonProperty("email")
    @Column(unique = true, nullable = false)
    open var email: String?,

    open var studentNo: String,

    @ManyToMany(targetEntity = ClassRoom::class)
    @JoinTable(
        name = "student_classroom",
        joinColumns = [JoinColumn(name = "student_id")],
        inverseJoinColumns = [JoinColumn(name = "classroom_id")]
    )

    open var classRooms: MutableList<ClassRoom> = mutableListOf(),

    @ManyToMany
    @JoinTable(
        name = "student_teacher",
        joinColumns = [JoinColumn(name = "student_id")],
        inverseJoinColumns = [JoinColumn(name = "teacher_id")]
    )
    open var teachers: MutableList<Teacher> = mutableListOf(),

    @Enumerated(EnumType.STRING)
    open var grade: GradeLevel,

    @ManyToMany
    @JoinTable(
        name = "student_parent",
        joinColumns = [JoinColumn(name = "student_id")],
        inverseJoinColumns = [JoinColumn(name = "parent_id")]
    )
    open var parents: MutableList<Parent> = mutableListOf(),

    open var createdAt: String?,

    open var modifiedAt: String?,

    ) {
    constructor() : this(
        firstName = "",
        lastName = "",
        email = "",
        studentNo = "",
        classRooms = mutableListOf(),
        teachers = mutableListOf(),
        grade = GradeLevel.ابتدایی,
        parents = mutableListOf(),
        createdAt = null,
        modifiedAt = null

    )

    override fun toString(): String {
        return "Student(id=$id, firstName=$firstName, lastName=$lastName, email=$email, studentNo='$studentNo', classRooms=$classRooms, teachers=$teachers, grade=$grade, parents=$parents, createdAt=$createdAt, modifiedAt=$modifiedAt)"
    }


}
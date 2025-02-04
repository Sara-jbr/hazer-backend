package com.haazer.school.entity

import jakarta.persistence.*

@Entity
@Table(name = "teacher")
open class Teacher(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long? = null,

    open val createdAt: String,

    @OneToOne(cascade = [CascadeType.ALL])
    open val user: User,

    @OneToMany(mappedBy = "teacher", cascade = [CascadeType.ALL])
    open val classroom: List<ClassRoom>,

    @ManyToMany(mappedBy = "teacher", cascade = [CascadeType.ALL])
    open val student: List<Student>,

    open val field: String
) {
    constructor() : this(
        createdAt = "",
        user = User(),
        classroom = listOf(),
        student = listOf(),
        field = ""
    )
}

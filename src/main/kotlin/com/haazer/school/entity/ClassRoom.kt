package com.haazer.school.entity

import jakarta.persistence.*


@Entity
@Table(name = "classRoom")
open class ClassRoom(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long? = null,

    open val name: String,

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    open val teacher: Teacher,

    @ManyToMany(mappedBy = "classRoom", cascade = [CascadeType.ALL])
    open val student: List<Student>

) {
    constructor() : this(name = "", teacher = Teacher(), student = listOf())
}
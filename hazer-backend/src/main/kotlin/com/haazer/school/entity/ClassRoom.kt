package com.haazer.school.entity

import jakarta.persistence.*

@Entity
@Table(name = "classRoom")
open class ClassRoom(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long? = null,

    open var className: String,

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @Column(nullable = false)
    open var teacher: Teacher? = null,

    @ManyToMany(mappedBy = "classRooms", cascade = [CascadeType.ALL])
    open var students: MutableList<Student> = mutableListOf(),

    open var createdAt: String?,

    open var modifiedAt: String?,

    ) {
    constructor() : this(
        className = "",
        students = mutableListOf(),
        teacher = Teacher(),
        createdAt = null,
        modifiedAt = null
    )

    override fun toString(): String {
        return "ClassRoom(id=$id, className='$className', teacher=$teacher, students=$students, createdAt=$createdAt, modifiedAt=$modifiedAt)"
    }


}
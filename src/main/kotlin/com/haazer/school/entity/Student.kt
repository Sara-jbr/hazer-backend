package com.haazer.school.entity

import com.haazer.school.entity.enumeration.SchoolGrade
import jakarta.persistence.*


@Entity
@Table(name = "student")
open class Student(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long? = null,

    open val createdAt: String,

    @OneToOne(cascade = [CascadeType.ALL])
    open val user: User,

    @ManyToMany(targetEntity = ClassRoom::class)
    @JoinTable(
        name = "student_classroom",
        joinColumns = [JoinColumn(name = "student_id")],
        inverseJoinColumns = [JoinColumn(name = "classroom_id")]
    )

    open var classRoom: List<ClassRoom>,

    @ManyToMany
    @JoinTable(
        name = "student_teacher",
        joinColumns = [JoinColumn(name = "student_id")],
        inverseJoinColumns = [JoinColumn(name = "teacher_id")]
    )
    open val teacher: List<Teacher>,

    @Enumerated(EnumType.STRING)
    open var grade: SchoolGrade,

    @ManyToMany
    @JoinTable(
        name = "student_parent",
        joinColumns = [JoinColumn(name = "student_id")],
        inverseJoinColumns = [JoinColumn(name = "parent_id")]
    )
    open val parent: List<Parent>,

    open val studentNo: String
) {
    constructor() : this(
        createdAt = "",
        user = User(),
        classRoom = listOf(),
        teacher = listOf(),
        grade = SchoolGrade.GRADE_1,
        parent = listOf(),
        studentNo = ""

    )
}
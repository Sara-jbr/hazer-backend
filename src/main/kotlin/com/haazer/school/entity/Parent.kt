package com.haazer.school.entity

import jakarta.persistence.*

@Entity
@Table(name = "parent")
open class Parent(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long? = null,

    open val createdAt: String,

    @OneToOne(cascade = [CascadeType.ALL])
    open val user: User,

    open val address: String,

    open val phoneNumber: String,

    open val mobileNumber: String,

    @ManyToMany(mappedBy = "parent", cascade = [CascadeType.ALL])
    open val student: List<Student>
) {
    constructor() : this(
        createdAt = "",
        user = User(),
        address = "",
        phoneNumber = "",
        mobileNumber = "",
        student = listOf()
    )
}
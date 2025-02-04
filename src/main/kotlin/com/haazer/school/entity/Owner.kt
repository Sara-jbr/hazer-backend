package com.haazer.school.entity

import jakarta.persistence.*

@Entity
@Table(name = "owner")
open class Owner(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long? = null,

    open val createdAt: String = "",

    @OneToOne(cascade = [CascadeType.ALL])
    open val user: User = User(),

    @OneToMany(mappedBy = "owner", cascade = [CascadeType.ALL])
    open val school: List<School> = listOf()

) {
    constructor() : this(
        createdAt = "",
        user = User(),
        school = listOf()
    )
}
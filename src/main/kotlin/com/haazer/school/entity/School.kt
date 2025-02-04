package com.haazer.school.entity

import jakarta.persistence.*

@Entity
@Table(name = "school")
open class School(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long? = null,

    open val name: String,

    @ManyToOne
    @JoinColumn(name = "owner_id")
    open val owner: Owner
) {

    constructor() : this(
        name = "",
        owner = Owner()
    )
}
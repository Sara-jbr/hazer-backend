package com.haazer.school.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*

@Entity
@Table(name = "schedule")
open class Schedule(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long? = null,

    open var startTime: String,
    open var endTime: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    @JsonBackReference
    open var school: School? = null
) {
    constructor() : this(
        startTime = "",
        endTime = "",
        school = School()
    )

    override fun toString(): String {
        return "Schedule(id=$id, startTime='$startTime', endTime='$endTime', schoolId=${school?.id})"
    }
}

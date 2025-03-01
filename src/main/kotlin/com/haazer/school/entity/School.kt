package com.haazer.school.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.haazer.school.entity.enumeration.GradeLevel
import com.haazer.school.entity.enumeration.SchoolType
import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
@Table(name = "schools")
open class School(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long? = null,

    @Column(nullable = false, unique = true)
    open var schoolName: String,

    open var address: String,

    open var contactNumber: String,

    @Enumerated(EnumType.STRING)
    open var schoolType: SchoolType,

    @Enumerated(EnumType.STRING)
    open var gradeLevel: GradeLevel,

    @OneToMany(mappedBy = "school", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore // Prevents infinite recursion
    open var schedules: MutableList<Schedule> = mutableListOf(),

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "owner_id", nullable = false)
    @JsonIgnore
    open var owner: Owner? = null,

     open var createdAt: String?,

    open var modifiedAt: String?,
) {
    constructor() : this(
        schoolName = "",
        address = "",
        contactNumber = "",
        schoolType = SchoolType.مدرسه,
        gradeLevel = GradeLevel.ابتدایی,
        schedules = mutableListOf(),
        owner = Owner(),
        createdAt = null,
        modifiedAt = null
    )

    fun addSchedule(schedule: Schedule) {
        schedules.add(schedule)
        schedule.school = this
    }

    override fun toString(): String {
        return "School(id=$id, schoolName='$schoolName', address='$address', " +
                "contactNumber='$contactNumber', schoolType=$schoolType, gradeLevel=$gradeLevel, " +
                "owner=$owner, schedules=${schedules.size})"
    }
}

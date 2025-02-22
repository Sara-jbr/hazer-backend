package com.haazer.school.service

import com.haazer.school.dto.OwnerDTO
import com.haazer.school.dto.SchoolDTO
import com.haazer.school.entity.Owner
import com.haazer.school.entity.School
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
interface SchoolService {

    fun getAllSchools(): List<School>
    fun getSchoolById(id: Long): School?
    fun deleteSchool(id: Long): Boolean
    fun updateSchool(id: Long, school : SchoolDTO): School
    fun createSchool(ownerId: Long, schoolDTO: SchoolDTO): School
}

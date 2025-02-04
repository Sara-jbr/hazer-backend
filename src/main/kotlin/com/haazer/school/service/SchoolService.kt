package com.haazer.school.service

import com.haazer.school.entity.School
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
interface SchoolService {

    fun createSchool(school: School)

    fun getAllSchools(): List<School>

    fun getSchoolById(id: Long): School?

    fun deleteSchool(id: Long): Boolean
}

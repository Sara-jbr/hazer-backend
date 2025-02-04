package com.haazer.school.service.impl

import com.haazer.school.entity.School
import com.haazer.school.repository.SchoolRepository
import com.haazer.school.service.SchoolService
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional

@ApplicationScoped
class SchoolServiceImpl : SchoolService {

    @Inject
    lateinit var schoolRepository: SchoolRepository

    @Transactional
    override fun createSchool(school: School) {
        schoolRepository.persist(school)
    }

    override fun getAllSchools(): List<School> {
        return schoolRepository.listAll()
    }

    override fun getSchoolById(id: Long): School? {
        return schoolRepository.findById(id)
    }

    @Transactional
    override fun deleteSchool(id: Long): Boolean {
        val school = schoolRepository.findById(id)
        return if (school != null) {
            schoolRepository.deleteById(id)
            true
        } else {
            false
        }
    }
}

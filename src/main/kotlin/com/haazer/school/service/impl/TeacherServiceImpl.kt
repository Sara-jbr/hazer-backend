package com.haazer.school.service.impl

import com.haazer.school.entity.Teacher
import com.haazer.school.repository.TeacherRepository
import com.haazer.school.service.TeacherService
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional

@ApplicationScoped
class TeacherServiceImpl : TeacherService {

    @Inject
    lateinit var teacherRepository: TeacherRepository

    @Transactional
    override fun createTeacher(teacher: Teacher) {
        teacherRepository.persist(teacher)
    }

    override fun getAllTeachers(): List<Teacher> {
        return teacherRepository.listAll()
    }

    override fun getTeacherById(id: Long): Teacher? {
        return teacherRepository.findById(id)
    }

    @Transactional
    override fun deleteTeacher(id: Long): Boolean {
        val teacher = teacherRepository.findById(id)
        return if (teacher != null) {
            teacherRepository.deleteById(id)
            true
        } else {
            false
        }
    }
}

package com.haazer.school.service.impl

import com.haazer.school.entity.Student
import com.haazer.school.repository.StudentRepository
import com.haazer.school.service.StudentService
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional

@ApplicationScoped
class StudentServiceImpl : StudentService {

    @Inject
    lateinit var studentRepository: StudentRepository

    @Transactional
    override fun createStudent(student: Student) {
        studentRepository.persist(student)
    }

    override fun getAllStudents(): List<Student> {
        return studentRepository.listAll()
    }

    override fun getStudentById(id: Long): Student? {
        return studentRepository.findById(id)
    }

    @Transactional
    override fun deleteStudent(id: Long): Boolean {
        val student = studentRepository.findById(id)
        return if (student != null) {
            studentRepository.deleteById(id)
            true
        } else {
            false
        }
    }
}

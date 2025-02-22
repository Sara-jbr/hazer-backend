package com.haazer.school.service

import com.haazer.school.dto.StudentDTO
import com.haazer.school.entity.Student
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
interface StudentService {

    fun getAllStudents(): List<Student>
    fun getStudentById(id: Long): Student?
    fun deleteStudent(id: Long): Boolean
    fun updateStudent(id: Long, student: StudentDTO): Student
    fun createStudent(student: StudentDTO): Student
}
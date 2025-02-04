package com.haazer.school.service

import com.haazer.school.entity.Student
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
interface StudentService {

    fun createStudent(student: Student)
    fun getAllStudents(): List<Student>

    fun getStudentById(id: Long): Student?
    fun deleteStudent(id: Long): Boolean
}

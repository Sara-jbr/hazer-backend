package com.haazer.school.service

import com.haazer.school.entity.Teacher
import jakarta.enterprise.context.ApplicationScoped


@ApplicationScoped
interface TeacherService {

    fun createTeacher(teacher: Teacher)
    fun getAllTeachers(): List<Teacher>
    fun getTeacherById(id: Long): Teacher?
    fun deleteTeacher(id: Long): Boolean
}

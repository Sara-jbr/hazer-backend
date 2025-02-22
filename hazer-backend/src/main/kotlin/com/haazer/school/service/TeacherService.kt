package com.haazer.school.service

import com.haazer.school.dto.TeacherDTO
import com.haazer.school.entity.Teacher
import jakarta.enterprise.context.ApplicationScoped


@ApplicationScoped
interface TeacherService {

    fun getAllTeachers(): List<Teacher>
    fun getTeacherById(id: Long): Teacher?
    fun deleteTeacher(id: Long): Boolean
    fun updateTeacher(id: Long, teacher: TeacherDTO): Teacher
    fun createTeacher(teacher: TeacherDTO): Teacher
}
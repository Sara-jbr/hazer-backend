package com.haazer.school.service.impl

import com.fasterxml.jackson.annotation.JsonProperty
import com.haazer.school.dto.TeacherDTO
import com.haazer.school.entity.ClassRoom
import com.haazer.school.entity.Student
import com.haazer.school.entity.Teacher
import com.haazer.school.repository.TeacherRepository
import com.haazer.school.service.TeacherService
import com.haazer.school.util.CommonUtil
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.ZonedDateTime

@ApplicationScoped
class TeacherServiceImpl : TeacherService {

    private val logger: Logger = LoggerFactory.getLogger(TeacherServiceImpl::class.java)

    @Inject
    lateinit var teacherRepository: TeacherRepository

    @Transactional
    override fun createTeacher(teacherDTO: TeacherDTO): Teacher {
        logger.info("createTeacher - Data: {}", teacherDTO)

        val teacher = Teacher(
            firstName = teacherDTO.firstName,
            lastName = teacherDTO.lastName,
            email = teacherDTO.email,
            field = teacherDTO.field,
            classRooms = teacherDTO.classRooms,
            students = teacherDTO.students,
            createdAt = CommonUtil.gregorianToJalali(ZonedDateTime.now()),
            modifiedAt = null
        )

        teacherRepository.persist(teacher)
        teacherRepository.flush()

        logger.info("Teacher {}: created successfully.", teacher)
        return teacher
    }

    override fun getAllTeachers(): List<Teacher> = teacherRepository.listAll()

    override fun getTeacherById(id: Long): Teacher? = teacherRepository.findById(id)

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

    @Transactional
    override fun updateTeacher(id: Long, teacherDTO: TeacherDTO): Teacher {
        val existingTeacher = teacherRepository.findById(id)
            ?: throw Exception("Teacher not found with ID: $id")

        logger.info("updateTeacher - Updating Teacher ID: {}, New Data: {}", id, teacherDTO)

        existingTeacher.firstName = teacherDTO.firstName
        existingTeacher.lastName = teacherDTO.lastName
        existingTeacher.classRooms = teacherDTO.classRooms
        existingTeacher.email = teacherDTO.email
        existingTeacher.field = teacherDTO.field
        existingTeacher.createdAt = existingTeacher.createdAt
        existingTeacher.students = teacherDTO.students
        existingTeacher.modifiedAt = CommonUtil.gregorianToJalali(ZonedDateTime.now())

        teacherRepository.persist(existingTeacher)
        teacherRepository.flush()

        logger.info("Teacher {}: updated successfully.", existingTeacher)
        return existingTeacher
    }
}
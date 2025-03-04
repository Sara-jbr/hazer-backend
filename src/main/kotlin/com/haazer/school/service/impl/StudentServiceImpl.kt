package com.haazer.school.service.impl


import com.fasterxml.jackson.annotation.JsonProperty
import com.haazer.school.dto.SchoolDTO
import com.haazer.school.dto.StudentDTO
import com.haazer.school.entity.*
import com.haazer.school.entity.enumeration.GradeLevel
import com.haazer.school.entity.enumeration.SchoolType
import com.haazer.school.repository.OwnerRepository
import com.haazer.school.repository.ParentRepository
import com.haazer.school.repository.SchoolRepository
import com.haazer.school.repository.StudentRepository
import com.haazer.school.service.SchoolService
import com.haazer.school.service.StudentService
import com.haazer.school.util.CommonUtil
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.ZonedDateTime

@ApplicationScoped
class StudentServiceImpl : StudentService {

    private val logger: Logger = LoggerFactory.getLogger(StudentServiceImpl::class.java)

    @Inject
    lateinit var studentRepository: StudentRepository

    @Inject
    lateinit var parentRepository: ParentRepository



    @Transactional
    override fun createStudent(studentDTO: StudentDTO): Student {
        logger.info("createStudent - Data: {}", studentDTO)

        val student = Student(
            firstName = studentDTO.firstName,
            lastName = studentDTO.lastName,
            email = studentDTO.email,
            studentNo = studentDTO.studentNo,
            grade = studentDTO.grade,
            createdAt = CommonUtil.gregorianToJalali(ZonedDateTime.now()),
            modifiedAt = null
        )

        studentRepository.persist(student)
        studentRepository.flush()

        logger.info(".{}: دانش آموز با موفقیت ذخیره شد", student)
        return student
    }

    override fun getAllStudents(): List<Student> = studentRepository.listAll()

    override fun getStudentById(id: Long): Student? = studentRepository.findById(id)

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

    @Transactional
    override fun updateStudent(id: Long, studentDTO: StudentDTO): Student {
        val existingStudent = studentRepository.findById(id)
            ?: throw Exception("Student not found with ID: $id")

        logger.info("updateStudent - Updating Student ID: {}, New Data: {}", id, studentDTO)

        existingStudent.firstName = studentDTO.firstName
        existingStudent.lastName = studentDTO.lastName
        existingStudent.email = studentDTO.email
        existingStudent.studentNo = studentDTO.studentNo
        existingStudent.modifiedAt = CommonUtil.gregorianToJalali(ZonedDateTime.now())
        existingStudent.createdAt = existingStudent.createdAt
        existingStudent.grade = studentDTO.grade
//        existingStudent.parents = studentDTO.parents

        studentRepository.persist(existingStudent)
        studentRepository.flush()

        logger.info(".{}: دانش آموز با موفقیت ذخیره شد", existingStudent)
        return existingStudent
    }
}
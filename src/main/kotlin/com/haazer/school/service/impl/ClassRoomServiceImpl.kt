package com.haazer.school.service.impl


import com.haazer.school.dto.ClassRoomDTO
import com.haazer.school.entity.ClassRoom
import com.haazer.school.repository.ClassRoomRepository
import com.haazer.school.repository.StudentRepository
import com.haazer.school.repository.TeacherRepository
import com.haazer.school.service.ClassRoomService
import com.haazer.school.util.CommonUtil
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import jakarta.ws.rs.NotFoundException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.ZonedDateTime

@ApplicationScoped
class ClassRoomServiceImpl : ClassRoomService {

    private val logger: Logger = LoggerFactory.getLogger(ClassRoomServiceImpl::class.java)

    @Inject
    lateinit var classRoomRepository: ClassRoomRepository

    @Inject
    lateinit var teacherRepository: TeacherRepository

    @Inject
    lateinit var studentRepository: StudentRepository


    @Transactional
    override fun createClassRoom(classRoomDTO: ClassRoomDTO): ClassRoom {

        val classroom = ClassRoom(
            className = classRoomDTO.className,
            teacher = null,
            students = null,
            createdAt = CommonUtil.gregorianToJalali(ZonedDateTime.now()),
            modifiedAt = null
        )

        classRoomRepository.persist(classroom)
        return classroom
    }

    override fun getAllClassRooms(): List<ClassRoom> = classRoomRepository.listAll()

    override fun getClassRoomById(id: Long): ClassRoom? = classRoomRepository.findById(id)

    @Transactional
    override fun deleteClassRoom(id: Long): Boolean {
        val classroom = classRoomRepository.findById(id)
        return if (classroom != null) {
            classRoomRepository.deleteById(id)
            true
        } else {
            false
        }
    }

    @Transactional
    override fun updateClassRoom(id: Long, classRoomDTO: ClassRoomDTO): ClassRoom {
        val existingClassRoom = classRoomRepository.findById(id)
            ?: throw Exception("Classroom not found with ID: $id")

        logger.info("updateClassroom - Updating Classroom ID: {}, New Data: {}", id, classRoomDTO)

        existingClassRoom.className = classRoomDTO.className
//        existingClassRoom.students = classRoomDTO.students
//        existingClassRoom.teacher = classRoomDTO.teacher
        existingClassRoom.createdAt = existingClassRoom.createdAt
        existingClassRoom.modifiedAt = CommonUtil.gregorianToJalali(ZonedDateTime.now())

        classRoomRepository.persist(existingClassRoom)
        classRoomRepository.flush()

        logger.info(".{}: کلاس با موفقیت ذخیره شد", existingClassRoom)
        return existingClassRoom
    }

    @Transactional
    override fun assignStudentToClassroom(classroomId: Long, studentId: Long) {
        val classroom = classRoomRepository.findById(classroomId) ?: throw Exception("class not found")
        val student = studentRepository.findById(studentId) ?: throw Exception("student not found")

        classroom.students?.add(student)
        student.classrooms.add(classroom)

        classRoomRepository.persist(classroom)
        studentRepository.persist(student)
    }
    @Transactional
    override fun assignTeacherToClassroom(classroomId: Long, teacherId: Long) {
        val classroom = classRoomRepository.findById(classroomId)?: throw Exception("class not found")
        val teacher = teacherRepository.findById(teacherId)?: throw Exception("teacher not found")

        classroom.teacher = teacher

        classRoomRepository.persist(classroom)
    }
}
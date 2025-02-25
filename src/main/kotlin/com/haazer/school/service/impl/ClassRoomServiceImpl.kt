package com.haazer.school.service.impl


import com.haazer.school.dto.ClassRoomDTO
import com.haazer.school.entity.ClassRoom
import com.haazer.school.repository.ClassRoomRepository
import com.haazer.school.service.ClassRoomService
import com.haazer.school.util.CommonUtil
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.ZonedDateTime

@ApplicationScoped
class ClassRoomServiceImpl : ClassRoomService {

    private val logger: Logger = LoggerFactory.getLogger(ClassRoomServiceImpl::class.java)

    @Inject
    lateinit var classRoomRepository: ClassRoomRepository

    @Transactional
    override fun createClassRoom(classRoomDTO: ClassRoomDTO): ClassRoom {
        logger.info("createClassroom - Data: {}", classRoomDTO)

        val classroom = ClassRoom(
            className = classRoomDTO.className,
            teacher = classRoomDTO.teacher,
            students = mutableListOf(),
            createdAt = CommonUtil.gregorianToJalali(ZonedDateTime.now()),
            modifiedAt = null
        )

        classRoomRepository.persist(classroom)
        classRoomRepository.flush()

        logger.info("Classroom {}: created successfully.", classroom)
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
        existingClassRoom.students = classRoomDTO.students
        existingClassRoom.teacher = classRoomDTO.teacher
        existingClassRoom.createdAt = existingClassRoom.createdAt
        existingClassRoom.modifiedAt = CommonUtil.gregorianToJalali(ZonedDateTime.now())

        classRoomRepository.persist(existingClassRoom)
        classRoomRepository.flush()

        logger.info("Classroom {}: updated successfully.", existingClassRoom)
        return existingClassRoom
    }
}
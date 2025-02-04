package com.haazer.school.service.impl

import com.haazer.school.entity.ClassRoom
import com.haazer.school.repository.ClassRoomRepository
import com.haazer.school.service.ClassRoomService
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional

@ApplicationScoped
class ClassRoomServiceImpl : ClassRoomService {

    @Inject
    lateinit var classRoomRepository: ClassRoomRepository

    @Transactional
    override fun createClassRoom(classRoom: ClassRoom) {
        classRoomRepository.persist(classRoom)
    }

    override fun getAllClassRooms(): List<ClassRoom> {
        return classRoomRepository.listAll()
    }

    override fun getClassRoomById(id: Long): ClassRoom? {
        return classRoomRepository.findById(id)
    }

    @Transactional
    override fun deleteClassRoom(id: Long): Boolean {
        val classRoom = classRoomRepository.findById(id)
        return if (classRoom != null) {
            classRoomRepository.deleteById(id)
            true
        } else {
            false
        }
    }
}

package com.haazer.school.service

import com.haazer.school.entity.ClassRoom
import jakarta.enterprise.context.ApplicationScoped


@ApplicationScoped
interface ClassRoomService {

    fun createClassRoom(classRoom: ClassRoom)

    fun getAllClassRooms(): List<ClassRoom>

    fun getClassRoomById(id: Long): ClassRoom?

    fun deleteClassRoom(id: Long): Boolean
}

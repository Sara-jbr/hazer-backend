package com.haazer.school.service

import com.haazer.school.dto.ClassRoomDTO
import com.haazer.school.entity.ClassRoom
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
interface ClassRoomService {

    fun getAllClassRooms(): List<ClassRoom>
    fun getClassRoomById(id: Long): ClassRoom?
    fun deleteClassRoom(id: Long): Boolean
    fun updateClassRoom(id: Long, classRoomDTO: ClassRoomDTO): ClassRoom
    fun createClassRoom(classRoom: ClassRoomDTO): ClassRoom
    fun assignTeacherToClassroom(classroomId: Long, teacherId: Long)
    fun assignStudentToClassroom(classroomId: Long, studentId: Long)
}
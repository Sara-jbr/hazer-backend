package com.haazer.school.repository

import com.haazer.school.entity.ClassRoom
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class ClassRoomRepository : PanacheRepository<ClassRoom>

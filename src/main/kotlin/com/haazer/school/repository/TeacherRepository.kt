package com.haazer.school.repository

import com.haazer.school.entity.Teacher
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class TeacherRepository : PanacheRepository<Teacher>
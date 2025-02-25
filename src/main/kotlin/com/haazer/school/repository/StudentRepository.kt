package com.haazer.school.repository

import com.haazer.school.entity.Student
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class StudentRepository : PanacheRepository<Student>
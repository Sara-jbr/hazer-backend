package com.haazer.school.repository

import com.haazer.school.entity.School
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class SchoolRepository : PanacheRepository<School>
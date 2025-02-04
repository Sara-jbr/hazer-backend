package com.haazer.school.repository


import com.haazer.school.entity.SchoolAdmin
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class SchoolAdminRepository : PanacheRepository<SchoolAdmin>
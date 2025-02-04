package com.haazer.school.service

import com.haazer.school.dto.SchoolAdminDTO
import com.haazer.school.entity.SchoolAdmin
import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.core.Response


@ApplicationScoped
interface SchoolAdminService {

    fun createSchoolAdmin(schoolAdmin: SchoolAdmin)
    fun getAllSchoolAdmins(): List<SchoolAdmin>
    fun getSchoolAdminById(id: Long): SchoolAdmin?
    fun deleteSchoolAdmin(id: Long): Boolean
    abstract fun registerUser(schoolAdmin: SchoolAdminDTO): Response
}

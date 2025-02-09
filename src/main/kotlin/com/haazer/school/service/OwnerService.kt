package com.haazer.school.service

import com.haazer.school.dto.OwnerDTO
import com.haazer.school.entity.Owner
import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.core.Response

@ApplicationScoped
interface OwnerService {
    fun getAllOwners(): List<Owner>
    fun getOwnerById(id: Long): Owner?
    fun deleteOwner(id: Long): Boolean
    fun updateOwner(id: Long, owner :OwnerDTO): Owner
    fun registerUser(owner: OwnerDTO): Owner
    fun login(username: String, password: String): Response
}

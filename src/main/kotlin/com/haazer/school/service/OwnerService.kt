package com.haazer.school.service

import com.haazer.school.entity.Owner
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
interface OwnerService {

    fun createOwner(owner: Owner)

    fun getAllOwners(): List<Owner>

    fun getOwnerById(id: Long): Owner?

    fun deleteOwner(id: Long): Boolean
}

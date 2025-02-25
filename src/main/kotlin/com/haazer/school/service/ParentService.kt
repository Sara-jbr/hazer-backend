package com.haazer.school.service

import com.haazer.school.dto.ParentDTO
import com.haazer.school.entity.Parent
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
interface ParentService {

    fun getAllParents(): List<Parent>
    fun getParentById(id: Long): Parent?
    fun deleteParent(id: Long): Boolean
    fun updateParent(id: Long, parent: ParentDTO): Parent
    fun createParent(parent: ParentDTO): Parent
}
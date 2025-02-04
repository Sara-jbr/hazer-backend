package com.haazer.school.service

import com.haazer.school.entity.Parent
import jakarta.enterprise.context.ApplicationScoped


@ApplicationScoped
interface ParentService {

    fun createParent(parent: Parent)

    fun getAllParents(): List<Parent>

    fun getParentById(id: Long): Parent?

    fun deleteParent(id: Long): Boolean
}

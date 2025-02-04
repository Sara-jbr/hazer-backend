package com.haazer.school.service.impl

import com.haazer.school.entity.Parent
import com.haazer.school.repository.ParentRepository
import com.haazer.school.service.ParentService
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional

@ApplicationScoped
class ParentServiceImpl : ParentService {

    @Inject
    lateinit var parentRepository: ParentRepository

    @Transactional
    override fun createParent(parent: Parent) {
        parentRepository.persist(parent)
    }

    override fun getAllParents(): List<Parent> {
        return parentRepository.listAll()
    }

    override fun getParentById(id: Long): Parent? {
        return parentRepository.findById(id)
    }

    @Transactional
    override fun deleteParent(id: Long): Boolean {
        val parent = parentRepository.findById(id)
        return if (parent != null) {
            parentRepository.deleteById(id)
            true
        } else {
            false
        }
    }
}

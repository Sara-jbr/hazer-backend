package com.haazer.school.service.impl

import com.haazer.school.entity.Owner
import com.haazer.school.repository.OwnerRepository
import com.haazer.school.service.OwnerService
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional

@ApplicationScoped
class OwnerServiceImpl : OwnerService {

    @Inject
    lateinit var ownerRepository: OwnerRepository

    @Transactional
    override fun createOwner(owner: Owner) {
        ownerRepository.persist(owner)
    }

    override fun getAllOwners(): List<Owner> {
        return ownerRepository.listAll()
    }

    override fun getOwnerById(id: Long): Owner? {
        return ownerRepository.findById(id)
    }

    @Transactional
    override fun deleteOwner(id: Long): Boolean {
        val owner = ownerRepository.findById(id)
        return if (owner != null) {
            ownerRepository.deleteById(id)
            true
        } else {
            false
        }
    }
}

package com.haazer.school.service.impl

import com.haazer.school.dto.ParentDTO
import com.haazer.school.entity.Parent
import com.haazer.school.repository.ParentRepository
import com.haazer.school.service.ParentService
import com.haazer.school.util.CommonUtil
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.ZonedDateTime

@ApplicationScoped
class ParentServiceImpl : ParentService {

    private val logger: Logger = LoggerFactory.getLogger(ParentServiceImpl::class.java)

    @Inject
    lateinit var parentRepository: ParentRepository

    @Transactional
    override fun createParent(parentDTO: ParentDTO): Parent {
        logger.info("createParent - Data: {}", parentDTO)

        val parent = Parent(
            firstName = parentDTO.firstName,
            lastName = parentDTO.lastName,
            email = parentDTO.email,
            address = CommonUtil.gregorianToJalali(ZonedDateTime.now()),
            phoneNumber = parentDTO.phoneNumber,
            mobileNumber = parentDTO.mobileNumber,
            students = mutableListOf(),
            createdAt = CommonUtil.gregorianToJalali(ZonedDateTime.now()),
            modifiedAt = null
        )

        parentRepository.persist(parent)
        parentRepository.flush()

        logger.info("Parent {}: created successfully.", parent)
        return parent
    }

    override fun getAllParents(): List<Parent> = parentRepository.listAll()

    override fun getParentById(id: Long): Parent? = parentRepository.findById(id)

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

    @Transactional
    override fun updateParent(id: Long, parentDTO: ParentDTO): Parent {
        val existingParent = parentRepository.findById(id)
            ?: throw Exception("Parent not found with ID: $id")

        logger.info("updateParent - Updating Parent ID: {}, New Data: {}", id, parentDTO)

        existingParent.firstName = parentDTO.firstName
        existingParent.lastName = parentDTO.lastName
        existingParent.email = parentDTO.email
        existingParent.modifiedAt = CommonUtil.gregorianToJalali(ZonedDateTime.now())
        existingParent.createdAt = existingParent.createdAt
        existingParent.address = existingParent.address
        existingParent.mobileNumber = existingParent.mobileNumber
        existingParent.phoneNumber = existingParent.phoneNumber
        existingParent.students = existingParent.students

        parentRepository.persist(existingParent)
        parentRepository.flush()

        logger.info("Parent {}: updated successfully.", existingParent)
        return existingParent
    }
}
package com.haazer.school.service.impl

import com.haazer.school.dto.SchoolDTO
import com.haazer.school.entity.Owner
import com.haazer.school.entity.Schedule
import com.haazer.school.entity.School
import com.haazer.school.entity.enumeration.GradeLevel
import com.haazer.school.entity.enumeration.SchoolType
import com.haazer.school.repository.OwnerRepository
import com.haazer.school.repository.SchoolRepository
import com.haazer.school.service.SchoolService
import com.haazer.school.util.CommonUtil
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.ZonedDateTime

@ApplicationScoped
class SchoolServiceImpl : SchoolService {

    private val logger: Logger = LoggerFactory.getLogger(SchoolServiceImpl::class.java)

    @Inject
    lateinit var ownerRepository: OwnerRepository

    @Inject
    lateinit var schoolRepository: SchoolRepository

    @Transactional
    override fun createSchool(ownerId: Long, schoolDTO: SchoolDTO): School {
        val owner = ownerRepository.findById(ownerId)
            ?: throw Exception("Owner not found")

        logger.info("createSchool - Owner: {}, School: {}", ownerId, schoolDTO)

        val createdAtJalali = schoolDTO.createdAt?.let {
            CommonUtil.gregorianToJalali(it) // Converts to Jalali date as a String
        }
        // Create the school entity
        val saveSchool = School(
            schoolName = schoolDTO.schoolName,
            address = schoolDTO.address,
            contactNumber = schoolDTO.contactNumber,
            schoolType = SchoolType.مدرسه,
            gradeLevel = GradeLevel.ابتدایی,
            createdAt = createdAtJalali,  // This should be a String representing Jalali date
            modifiedAt = null
        )

        schoolDTO.schedules.forEach { scheduleDto ->
            val schedule = Schedule(
                startTime = scheduleDto.startTime,
                endTime = scheduleDto.endTime,
                school = saveSchool
            )
            saveSchool.schedules.add(schedule)
        }


        saveSchool.owner = owner
        owner.addSchool(saveSchool)

        schoolRepository.persist(saveSchool)
        schoolRepository.flush()


        logger.info("School saved successfully: {}", saveSchool)

        return saveSchool
    }


    override fun getAllSchools(): List<School> = schoolRepository.listAll()

    override fun getSchoolById(id: Long): School? = schoolRepository.findById(id)

    @Transactional
    override fun deleteSchool(id: Long): Boolean {
        val school = schoolRepository.findById(id)
        return if (school != null) {
            schoolRepository.deleteById(id)
            true
        } else {
            false
        }
    }

    @Transactional
    override fun updateSchool(id: Long, school: SchoolDTO): School {

        val existingSchool = schoolRepository.findById(id)
            ?: throw Exception("School not found with ID: $id")

        logger.info("updateSchool - Updating School ID: {}, New Data: {}", id, school)

        val createdAtJalali = school.createdAt?.let {
            CommonUtil.gregorianToJalali(it) // Converts to Jalali date as a String
        }
        // Update basic fields
        existingSchool.schoolName = school.schoolName
        existingSchool.address = school.address
        existingSchool.contactNumber = school.contactNumber
        existingSchool.modifiedAt = createdAtJalali


        existingSchool.schoolType = try {
            SchoolType.valueOf(school.schoolType.toString())
        } catch (e: IllegalArgumentException) {
            throw Exception("Invalid school type")
        }


        existingSchool.gradeLevel = try {
            GradeLevel.valueOf(school.gradeLevel.toString())
        } catch (e: IllegalArgumentException) {
            throw Exception("Invalid grade level")
        }


        existingSchool.schedules.clear()
        school.schedules.forEach { scheduleDto ->
            val schedule = Schedule(
                startTime = scheduleDto.startTime,
                endTime = scheduleDto.endTime,
                school = existingSchool
            )
            existingSchool.schedules.add(schedule)
        }

        schoolRepository.persist(existingSchool)
        schoolRepository.flush()

        logger.info("School updated successfully: {}", existingSchool)

        return existingSchool
    }
}


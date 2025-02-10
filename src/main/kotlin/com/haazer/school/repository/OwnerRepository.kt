package com.haazer.school.repository

import com.haazer.school.entity.Owner
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped


@ApplicationScoped
class OwnerRepository : PanacheRepository<Owner> {
    fun findByEmail(email: String): Owner? {
        return find("email", email).firstResult()
    }

    fun findByEmailOrUsername(identifier: String): Owner? {
        return find("email = ?1 or userName = ?1", identifier).firstResult()
    }
}




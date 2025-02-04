package com.haazer.school.service.impl

import com.haazer.school.entity.User
import com.haazer.school.repository.UserRepository
import com.haazer.school.service.UserService
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional

@ApplicationScoped
class UserServiceImpl : UserService {

    @Inject
    lateinit var userRepository: UserRepository

    @Transactional
    override fun createUser(user: User) {
        userRepository.persist(user)
    }

    override fun getAllUsers(): List<User> {
        return userRepository.listAll()
    }

    override fun getUserById(id: Long): User? {
        return userRepository.findById(id)
    }

    @Transactional
    override fun deleteUser(id: Long): Boolean {
        val user = userRepository.findById(id)
        return if (user != null) {
            userRepository.deleteById(id)
            true
        } else {
            false
        }
    }
}

package com.haazer.school.service

import com.haazer.school.entity.User
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
interface UserService {

    fun createUser(user: User)
    fun getAllUsers(): List<User>
    fun getUserById(id: Long): User?
    fun deleteUser(id: Long): Boolean
}

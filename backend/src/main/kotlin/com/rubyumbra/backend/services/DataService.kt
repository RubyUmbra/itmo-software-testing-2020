package com.rubyumbra.backend.services

import com.rubyumbra.backend.models.User
import com.rubyumbra.backend.repositories.UserRepository
import com.rubyumbra.backend.security.UserDetailsImpl
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class DataService(private val userRepository: UserRepository) {
    fun getUserByUid(uid: Long) = userRepository.findById(uid).get()
    fun getAllUsers(): List<User> = userRepository.findAll()
    fun getCurrentUser() = userRepository
        .findById((SecurityContextHolder.getContext().authentication.principal as UserDetailsImpl).id).get()
}

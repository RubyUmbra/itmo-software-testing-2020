package com.rubyumbra.backend.repositories

import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository

import com.rubyumbra.backend.models.User

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): User?
    fun existsByUsername(username: String): Boolean
    fun existsByEmail(email: String): Boolean
}

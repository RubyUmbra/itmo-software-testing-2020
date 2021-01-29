package com.rubyumbra.backend.security

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import com.rubyumbra.backend.repositories.UserRepository

@Service
class UserDetailsServiceImpl(
    private val userRepository: UserRepository
) : UserDetailsService {
    @Transactional
    override fun loadUserByUsername(username: String?): UserDetails =
        if (username == null) throw UsernameNotFoundException(username)
        else UserDetailsImpl.build(
            userRepository.findByUsername(username)
                ?: throw UsernameNotFoundException(username)
        )
}

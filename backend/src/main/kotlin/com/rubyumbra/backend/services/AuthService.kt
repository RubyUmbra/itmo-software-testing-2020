package com.rubyumbra.backend.services

import com.rubyumbra.backend.models.User
import com.rubyumbra.backend.payloads.requests.LoginRequest
import com.rubyumbra.backend.payloads.requests.SignUpRequest
import com.rubyumbra.backend.payloads.responses.JwtResponse
import com.rubyumbra.backend.payloads.responses.MessageResponse
import com.rubyumbra.backend.repositories.UserRepository
import com.rubyumbra.backend.security.UserDetailsImpl
import com.rubyumbra.backend.security.jwt.JwtUtils
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val authenticationManager: AuthenticationManager,
    private val userRepository: UserRepository,
    private val encoder: PasswordEncoder,
    private val jwtUtils: JwtUtils
) {
    fun authenticateUser(loginRequest: LoginRequest): ResponseEntity<*> {
        val authentication: Authentication = authenticationManager
            .authenticate(UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password))

        SecurityContextHolder.getContext().authentication = authentication
        val jwt: String = jwtUtils.generateJwtToken(authentication)
        val userDetails: UserDetailsImpl = authentication.principal as UserDetailsImpl
        val roles: List<String> = userDetails.authorities.map { it.authority }

        return ResponseEntity.ok(
            JwtResponse(
                jwt,
                userDetails.id,
                userDetails.username,
                userDetails.email,
                roles
            )
        )
    }

    fun registerUser(signUpRequest: SignUpRequest): ResponseEntity<*> {
        if (userRepository.existsByUsername(signUpRequest.username)) {
            return ResponseEntity
                .badRequest()
                .body(MessageResponse("Error: Username is already taken!"))
        }
        if (userRepository.existsByEmail(signUpRequest.email)) {
            return ResponseEntity
                .badRequest()
                .body(MessageResponse("Error: Email is already in use!"))
        }
        userRepository.save(
            User(
                username = signUpRequest.username,
                email = signUpRequest.email,
                password = encoder.encode(signUpRequest.password)
            )
        )
        return ResponseEntity.ok(MessageResponse("User registered successfully!"))
    }
}

package com.rubyumbra.backend.controllers

import javax.validation.Valid

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import com.rubyumbra.backend.payloads.requests.LoginRequest
import com.rubyumbra.backend.payloads.requests.SignUpRequest
import com.rubyumbra.backend.services.AuthService

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
class AuthController(private val authService: AuthService) {
    @PostMapping("/signin")
    fun authenticateUser(
        @Valid
        @RequestBody
        loginRequest: LoginRequest
    ): ResponseEntity<*> = authService.authenticateUser(loginRequest)

    @PostMapping("/signup")
    fun registerUser(
        @Valid
        @RequestBody
        signUpRequest: SignUpRequest
    ): ResponseEntity<*> = authService.registerUser(signUpRequest)
}

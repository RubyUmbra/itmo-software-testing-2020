package com.rubyumbra.backend.payloads.requests

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class SignUpRequest(
    @NotBlank
    @Size(max = 64)
    val username: String,
    @Email
    @NotBlank
    @Size(max = 64)
    val email: String,
    @NotBlank
    val password: String,
    @NotBlank
    val isAdmin: Boolean = false
)

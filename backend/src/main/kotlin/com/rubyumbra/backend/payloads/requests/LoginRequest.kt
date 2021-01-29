package com.rubyumbra.backend.payloads.requests

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class LoginRequest(
    @NotBlank
    @Size(max = 64)
    var username: String,
    @NotBlank
    var password: String,
)

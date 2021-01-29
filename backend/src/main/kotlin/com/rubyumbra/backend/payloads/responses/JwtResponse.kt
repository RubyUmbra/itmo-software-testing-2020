package com.rubyumbra.backend.payloads.responses

data class JwtResponse(
    var accessToken: String,
    var id: Long,
    var username: String,
    var email: String,
    var roles: List<String>,
    var tokenType: String = "Bearer"
)

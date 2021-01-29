package com.rubyumbra.backend.models

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @NotBlank
    @Size(max = 64)
    @Column(unique = true)
    val username: String,
    @Email
    @NotBlank
    @Size(max = 64)
    @Column(unique = true)
    val email: String,
    @NotBlank
    @JsonIgnore
    val password: String,
    @NotBlank
    val isAdmin: Boolean = false
)

package com.rubyumbra.backend.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.authority.SimpleGrantedAuthority

import com.rubyumbra.backend.models.User
import com.fasterxml.jackson.annotation.JsonIgnore

data class UserDetailsImpl(
    val id: Long,
    private val username: String,
    val email: String,
    @JsonIgnore
    private val password: String,
    private val authorities: MutableCollection<out GrantedAuthority>
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = authorities
    override fun getPassword(): String = password
    override fun getUsername(): String = username

    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true

    override fun equals(other: Any?): Boolean = when {
        this === other -> true
        other == null || javaClass != other.javaClass -> false
        else -> id == (other as UserDetailsImpl).id
    }

    companion object {
        private const val serialVersionUID: Long = 1L
        private val user: Collection<GrantedAuthority> =
            listOf(SimpleGrantedAuthority("ROLE_USER"))
        private val admin: Collection<GrantedAuthority> =
            listOf(
                SimpleGrantedAuthority("ROLE_USER"),
                SimpleGrantedAuthority("ROLE_ADMIN")
            )

        fun build(user: User) = UserDetailsImpl(
            user.id,
            user.username,
            user.email,
            user.password,
            (if (user.isAdmin) admin else this.user).toMutableList()
        )
    }
}

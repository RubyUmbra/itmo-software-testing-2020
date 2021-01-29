package com.rubyumbra.backend

import com.rubyumbra.backend.controllers.DataController
import com.rubyumbra.backend.models.User
import com.rubyumbra.backend.security.UserDetailsServiceImpl
import com.rubyumbra.backend.security.jwt.AuthEntryPointJwt
import com.rubyumbra.backend.security.jwt.JwtUtils
import com.rubyumbra.backend.services.AuthService
import com.rubyumbra.backend.services.DataService
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.test.context.support.WithMockUser

@WebMvcTest
class DataControllerComponentTests(
    @Autowired
    private val dataController: DataController
) {
    @MockBean
    private val dataService: DataService? = null

    @MockBean
    private val authService: AuthService? = null

    @MockBean
    private val authenticationManager: AuthenticationManager? = null

    @MockBean
    private val jwtUtils: JwtUtils? = null

    @MockBean
    private val authEntryPointJwt: AuthEntryPointJwt? = null

    @MockBean
    private val userDetailsService: UserDetailsServiceImpl? = null

    @WithMockUser(roles = ["USER"])
    @Test
    fun testAllUsersList() {
        val users = listOf(
            User(0, "admin", "admin@local.com", "password"),
            User(1, "u1", "u1@local.com", "password"),
            User(2, "u2", "u2@local.com", "password"),
            User(3, "u3", "u3@local.com", "password")
        )
        given(this.dataService?.getAllUsers()).willReturn(users)

        val res = dataController.all()

        assertArrayEquals(users.toTypedArray(), res.toTypedArray())
    }
}

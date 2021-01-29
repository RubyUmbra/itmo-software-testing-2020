package com.rubyumbra.backend

import com.rubyumbra.backend.controllers.AuthController
import com.rubyumbra.backend.models.User
import com.rubyumbra.backend.payloads.requests.SignUpRequest
import com.rubyumbra.backend.repositories.UserRepository
import org.junit.Assert
import org.junit.ClassRule
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.http.HttpStatus
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest
@ContextConfiguration(initializers = [LoginTests.Initializer::class])
@Testcontainers
class LoginTests {
    @Autowired
    val userRepository: UserRepository? = null

    @Autowired
    val authController: AuthController? = null

    companion object {
        @ClassRule
        @Container
        val postgreSQLContainer: PostgreSQLContainer<*> =
            PostgreSQLContainer<PostgreSQLContainer<*>>("postgres:11.1")
                .withDatabaseName("integration-tests-db")
                .withUsername("name")
                .withPassword("pass")
    }


    object Initializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        override fun initialize(configurableApplicationContext: ConfigurableApplicationContext) {
            TestPropertyValues.of(
                "spring.datasource.url=${postgreSQLContainer.jdbcUrl}",
                "spring.datasource.username=${postgreSQLContainer.username}",
                "spring.datasource.password=${postgreSQLContainer.password}"
            ).applyTo(configurableApplicationContext.environment)
        }
    }

    @Test
    @Transactional
    fun successfulAuthorization() {
        val user = SignUpRequest("username", "mail@local.com", "password")
        userRepository?.save(User(0, "username", "mail@local.com", "password"))
        Assert.assertEquals(authController?.registerUser(user)?.statusCode, HttpStatus.OK)
    }
}

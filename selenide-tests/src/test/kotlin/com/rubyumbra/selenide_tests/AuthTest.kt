package com.rubyumbra.selenide_tests

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selectors.*
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.open
import com.codeborne.selenide.WebDriverRunner.url
import com.codeborne.selenide.logevents.SelenideLogger
import org.junit.jupiter.api.*
import io.qameta.allure.selenide.AllureSelenide

class AuthTest {
    @BeforeEach
    fun setUpAllure() = SelenideLogger.addListener("allure", AllureSelenide())

    @BeforeEach
    fun setUp() {
        Configuration.startMaximized = true

        // logout
        open("http://localhost:8081")
        element(byId("logoutButton")).click()

        // open page
        open("http://localhost:8081/login")
    }

    @Test
    @DisplayName("Проверяем авторизацию для существующего пользователя.")
    fun authAsAdmin() {
        element(byId("username")).value = "admin"
        element(byId("password")).value = "password"
        element(byTagName("button")).click()

        url().endsWith("profile")
        element(byXpath("//h3//strong[text()='admin']")).exists()
    }

    @Test
    @DisplayName("Проверяем что после логаута происходит переход на страницу авторизации.")
    fun checkLoginPageAfterLogout() {
        // log in
        element(byId("username")).value = "admin"
        element(byId("password")).value = "password"
        element(byTagName("button")).click()

        // log out
        open("http://localhost:8081")
        element(byId("logoutButton")).click()

        // check
        url().endsWith("login")
    }
}

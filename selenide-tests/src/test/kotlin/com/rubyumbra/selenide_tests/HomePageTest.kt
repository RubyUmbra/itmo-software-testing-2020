package com.rubyumbra.selenide_tests

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selectors.*
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.open
import com.codeborne.selenide.logevents.SelenideLogger
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.DisplayName
import io.qameta.allure.selenide.AllureSelenide

class HomePageTest {
    @BeforeEach
    fun setUpAllure() {
        SelenideLogger.addListener("allure", AllureSelenide())
    }

    @BeforeEach
    fun setUp() {
        Configuration.startMaximized = true

        // logout
        open("http://localhost:8081")
        element(byId("logoutButton")).click()

        // open page
        open("http://localhost:8081/")
    }

    @Test
    @DisplayName("Проверяем текст на домашней странице")
    fun checkHomePageText() {
        element(byXpath("//h1[text()='Home page']")).exists()
    }

    @Test
    @DisplayName("Проверяем что меню приложения отображается")
    fun checkAppMenu() {
        element(byLinkText("Home")).exists()
        element(byLinkText("Login")).exists()
        element(byLinkText("Register")).exists()
        element(byLinkText("Profile")).exists()
        element(byLinkText("LogOut")).exists()
    }
}

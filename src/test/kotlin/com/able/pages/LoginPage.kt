package com.able.pages

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Selectors.byXpath
import com.codeborne.selenide.Selectors.byClassName
import com.codeborne.selenide.Selenide.element
import io.qameta.allure.Step

class LoginPage : BasePage() {
    @Step(value = "Заполнение формы авторизации")
    fun field_login_form(login: String, password: String) {
        element(byXpath("//input[@placeholder = 'Логин']")).`val`(login)
        element(byXpath("//input[@placeholder = 'Пароль']")).`val`(password)
    }

    @Step("Проверка авторизованного пользователя")
    fun check_login(login: String) {
        element(byClassName("login_text")).shouldBe(text(login))
    }

    @Step("Проверка ошибок пустых полей")
    fun check_error_empty_field() {
        element(byXpath("(//div[contains(@class, 'el-form-item__error')])[1]")).shouldBe(text("Пожалуйста, заполните поле логин"))
        element(byXpath("(//div[contains(@class, 'el-form-item__error')])[2]")).shouldBe(text("Пожалуйста, заполните поле пароль"))
    }

    @Step(value = "Заполнение логина")
    fun field_login_input(login: String) {
        element(byXpath("//input[@placeholder = 'Логин']")).`val`(login)
    }

    @Step(value = "Заполнение пароля")
    fun field_password_input(password: String) {
        element(byXpath("//input[@placeholder = 'Пароль']")).`val`(password)
    }

    @Step("Проверка ошибки пароля")
    fun check_error_empty_password() {
        element(byXpath("(//div[contains(@class, 'el-form-item__error')])[1]")).shouldBe(text("Пожалуйста, заполните поле пароль"))
    }

    @Step("Проверка ошибки логина")
    fun check_error_empty_login() {
        element(byXpath("(//div[contains(@class, 'el-form-item__error')])[1]")).shouldBe(text("Пожалуйста, заполните поле логин"))
    }
}
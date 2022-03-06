package com.able.tests

import com.able.api.data.RequestLogin
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.junit.jupiter.api.Test
import com.able.pages.LoginPage
import org.junit.jupiter.api.DisplayName

@Feature(value = "Авторизация")
@DisplayName(value = "[KOTLIN][UI]")
class LoginPageTest : ConfigTest() {
    private val loginPage = LoginPage()

    @Test
    @Story(value = "Проверка авторизации")
    fun `Проверка авторизации`() {
        loginPage.open_page()
        loginPage.check_title(title = "Авторизация")
        loginPage.field_login_form(RequestLogin().login, RequestLogin().password)
        loginPage.click_button_with_name(name = "Войти")
        loginPage.check_login(login = RequestLogin().login)
    }

    @Test
    @Story(value = "Проверка ошибки пустых полей")
    fun `Проверка ошибки пустых полей`() {
        loginPage.open_page()
        loginPage.check_title(title = "Авторизация")
        loginPage.click_button_with_name(name = "Войти")
        loginPage.check_error_empty_field()
    }

    @Test
    @Story(value = "Проверка ошибки пустого пароля")
    fun `Проверка ошибки пустого пароля`() {
        loginPage.open_page()
        loginPage.check_title(title = "Авторизация")
        loginPage.field_login_input(RequestLogin(login = "test").login)
        loginPage.click_button_with_name(name = "Войти")
        loginPage.check_error_empty_password()
    }

    @Test
    @Story(value = "Проверка ошибки пустого логина")
    fun `Проверка ошибки пустого логина`() {
        loginPage.open_page()
        loginPage.check_title(title = "Авторизация")
        loginPage.field_password_input(RequestLogin(password = "test").password)
        loginPage.click_button_with_name(name = "Войти")
        loginPage.check_error_empty_login()
    }

    @Test
    @Story(value = "Проверка сообщения 'Такого пользователя не существует'")
    fun `Проверка сообщения 'Такого пользователя не существует'`() {
        loginPage.open_page()
        loginPage.check_title(title = "Авторизация")
        loginPage.field_login_form(
            RequestLogin(login = "test").login,
            RequestLogin(password = "test").password
        )
        loginPage.click_button_with_name(name = "Войти")
        loginPage.check_message_in_message_box(
            title = "Авторизация",
            text = "Такого пользователя не существует"
        )
    }

    @Test
    @Story(value = "Проверка сообщения 'Не верный пароль'")
    fun `Проверка сообщения 'Не верный пароль'`() {
        loginPage.open_page()
        loginPage.check_title(title = "Авторизация")
        loginPage.field_login_form(
            RequestLogin(login = "admin").login,
            RequestLogin(password = "test").password
        )
        loginPage.click_button_with_name(name = "Войти")
        loginPage.check_message_in_message_box(
            title = "Авторизация",
            text = "Неверный логин или пароль"
        )
    }

    @Test
    @Story(value = "Проверка сообщения 'Максимальная длина логина может быть 100 символов'")
    fun `Проверка сообщения 'Максимальная длина логина может быть 100 символов'`() {
        loginPage.open_page()
        loginPage.check_title(title = "Авторизация")
        loginPage.field_login_form(
            RequestLogin(login = loginPage.randomString).login,
            RequestLogin(password = "test").password
        )
        loginPage.click_button_with_name(name = "Войти")
        loginPage.check_message_in_message_box(
            title = "Авторизация",
            text = "Максимальная длина логина может быть 100 символов"
        )
    }

    @Test
    @Story(value = "Проверка сообщения 'Максимальная длина пароля может быть 100 символов'")
    fun `Проверка сообщения 'Максимальная длина пароля может быть 100 символов'`() {
        loginPage.open_page()
        loginPage.check_title(title = "Авторизация")
        loginPage.field_login_form(
            RequestLogin(login = "test").login,
            RequestLogin(password = loginPage.randomString).password
        )
        loginPage.click_button_with_name(name = "Войти")
        loginPage.check_message_in_message_box(
            title = "Авторизация",
            text = "Максимальная длина пароля может быть 100 символов"
        )
    }
}

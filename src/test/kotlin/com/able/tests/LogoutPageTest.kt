package com.able.tests

import com.able.api.data.RequestLogin
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.junit.jupiter.api.Test
import com.able.pages.LoginPage
import com.able.pages.LogoutPage
import org.junit.jupiter.api.DisplayName

@Feature(value = "Выход из системы")
@DisplayName(value = "[KOTLIN][UI]")
class LogoutPageTest : ConfigTest() {
    private val loginPage = LoginPage()
    private val logoutPage = LogoutPage()

    @Test
    @Story(value = "Проверка выхода из системы")
    fun `Проверка выхода из системы`() {
        loginPage.open_page()
        loginPage.check_title(title = "Авторизация")
        loginPage.field_login_form(RequestLogin().login, RequestLogin().password)
        loginPage.click_button_with_name(name = "Войти")
        loginPage.check_login(login = RequestLogin().login)
        logoutPage.logout()
        logoutPage.check_message_in_message_box(
            title = "Выход из системы",
            text = "Пользователь успешно вышел из системы"
        )
    }
}

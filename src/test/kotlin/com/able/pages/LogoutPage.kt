package com.able.pages

import com.codeborne.selenide.Selectors.byXpath
import com.codeborne.selenide.Selenide.element
import io.qameta.allure.Step

class LogoutPage : BasePage() {
    @Step("Выход из системы")
    fun logout() {
        element(byXpath("(//div[@class = 'exit']/button)[3]")).click()
    }
}
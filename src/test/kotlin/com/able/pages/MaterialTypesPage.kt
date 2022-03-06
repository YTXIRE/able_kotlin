package com.able.pages

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selectors.byXpath
import com.codeborne.selenide.Selenide.element
import io.qameta.allure.Step

class MaterialTypesPage : BasePage() {
    @Step("Получени ID последней записи")
    fun get_last_record_id(): Int {
        return element(byXpath("//tr[@class = 'el-table__row']/td/div")).text().toInt()
    }

    @Step("Проверка ошибок пустых полей")
    fun check_error_empty_fields() {
        element(byXpath("(//div[contains(@class, 'el-form-item__error')])[1]")).shouldBe(Condition.text("Пожалуйста, укажите название типа материала"))
        element(byXpath("(//div[contains(@class, 'el-form-item__error')])[2]")).shouldBe(Condition.text("Пожалуйста, выберите величину объема"))
    }
}
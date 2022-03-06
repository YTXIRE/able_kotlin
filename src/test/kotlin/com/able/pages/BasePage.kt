package com.able.pages

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selectors.byXpath
import com.codeborne.selenide.Selenide.*
import io.qameta.allure.Step
import org.apache.commons.lang3.RandomStringUtils


open class BasePage {
    val randomString: String = RandomStringUtils.randomAlphanumeric(101)

    @Step("Открытие страницы {url}")
    fun open_page(url: String = "/") {
        open(url)
        localStorage().clear()
    }

    @Step("Проверка заголовка")
    fun check_title(title: String, tag: String = "h2") {
        element(byXpath("//$tag")).shouldBe(text(title))
    }

    @Step("Проверка сообщения с заголовком - {title} и текстом - {text}")
    fun check_message_in_message_box(title: String, text: String) {
        element(byXpath("//h2[contains(@class, 'el-notification__title')]")).shouldBe(text(title))
        element(byXpath("//div[contains(@class, 'el-notification__content')]")).shouldBe(text(text))
    }

    @Step("Клик по элементу с текстом - {text}")
    fun click_element_with_text(text: String) {
        element(byXpath("//div[contains(text(), '$text')]")).click()
    }

    @Step("Клик по кнопке добавления")
    fun click_add_button(){
        element(byXpath("//button[@type = 'button' and contains(@class, 'is-circle')]")).click()
    }

    @Step("Проверка диалогового окна с заголовком - {title}")
    fun check_dialog_title(title: String) {
        element(byXpath("//span[@class = 'el-dialog__title']")).shouldBe(text(title))
    }

    @Step("Заполнение название")
    fun field_name(label: String, text: String) {
        element(byXpath("//label[text()[contains(.,'$label')]]/following::input")).sendKeys(text)
    }

    @Step("Выбор значения из селектора")
    fun choose_selector(label: String) {
        element(byXpath("//label[text()[contains(.,'$label')]]/following::input")).click()
        element(byXpath("//li/span")).click()
    }

    @Step("Клик по кнопке с именем - {name}")
    fun click_button_with_name(name: String) {
        element(byXpath("//*[text()[contains(.,'$name')]]")).click()
    }

    @Step("Проверка наличия элемента с текстом - {text}, на странице")
    fun check_text_in_page(text: String) {
        element(byXpath("//*[text()[contains(., '$text')]]")).shouldBe(visible)
    }

    @Step("Проверка ошибки - {message}")
    fun check_error_empty_field(message: String) {
        element(byXpath("(//div[contains(@class, 'el-form-item__error')])[1]")).shouldBe(text(message))
    }
}

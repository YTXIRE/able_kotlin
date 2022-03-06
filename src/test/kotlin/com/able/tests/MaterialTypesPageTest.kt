package com.able.tests

import com.able.api.data.RequestLogin
import com.able.data.Faker
import com.able.model.MaterialTypesActions
import com.able.pages.LoginPage
import com.able.pages.MaterialTypesPage
import com.able.utils.Helpers
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance


@Feature(value = "Типы материалов")
@DisplayName(value = "[KOTLIN][UI]")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MaterialTypesPageTest : ConfigTest() {
    private val loginPage = LoginPage()
    private val materialTypesPage = MaterialTypesPage()
    private val admin = Helpers().createAdmin()

    @Test
    @Story(value = "Проверка создания типа материала")
    fun `Проверка создания типа материала`() {
        val materialName = Faker.getName()
        loginPage.open_page()
        loginPage.check_title(title = "Авторизация")
        loginPage.field_login_form(
            RequestLogin(login = admin!!.login).login,
            RequestLogin(password = admin.password).password
        )
        loginPage.click_button_with_name(name = "Войти")
        loginPage.check_login(login = RequestLogin(login = admin.login).login)
        materialTypesPage.click_element_with_text("Типы материалов")
        materialTypesPage.check_title(title = "Типы материалов")
        materialTypesPage.click_add_button()
        materialTypesPage.check_dialog_title(title = "Создание типа материала")
        materialTypesPage.field_name(label = "Название", text = materialName)
        materialTypesPage.choose_selector(label = "Величина объема")
        materialTypesPage.click_button_with_name(name = "Сохранить")
        materialTypesPage.check_message_in_message_box(
            title = "Тип материала",
            text = "Новый тип материала успешно создан"
        )
        materialTypesPage.check_title(title = "Типы материалов")
        materialTypesPage.check_text_in_page(text = materialName)
        materialTypesPage.check_text_in_page(text = "Метр")
        MaterialTypesActions.remove_material_type_with_id(id = materialTypesPage.get_last_record_id())
    }

    @Test
    @Story(value = "Проверка нажатия кнопки 'Отмена' при создании типа материала")
    fun `Проверка нажатия кнопки 'Отмена' при создании типа материала`() {
        loginPage.open_page()
        loginPage.check_title(title = "Авторизация")
        loginPage.field_login_form(
            RequestLogin(login = admin!!.login).login,
            RequestLogin(password = admin.password).password
        )
        loginPage.click_button_with_name(name = "Войти")
        loginPage.check_login(login = RequestLogin(login = admin.login).login)
        materialTypesPage.click_element_with_text("Типы материалов")
        materialTypesPage.check_title(title = "Типы материалов")
        materialTypesPage.click_add_button()
        materialTypesPage.check_dialog_title(title = "Создание типа материала")
        materialTypesPage.click_button_with_name(name = "Отменить")
        materialTypesPage.check_title(title = "Типы материалов")
    }

    @Test
    @Story(value = "Проверка ошибкок пустых полей при создании типа материала")
    fun `Проверка ошибки ошибкок полей при создании типа материала`() {
        loginPage.open_page()
        loginPage.check_title(title = "Авторизация")
        loginPage.field_login_form(
            RequestLogin(login = admin!!.login).login,
            RequestLogin(password = admin.password).password
        )
        loginPage.click_button_with_name(name = "Войти")
        loginPage.check_login(login = RequestLogin(login = admin.login).login)
        materialTypesPage.click_element_with_text("Типы материалов")
        materialTypesPage.check_title(title = "Типы материалов")
        materialTypesPage.click_add_button()
        materialTypesPage.check_dialog_title(title = "Создание типа материала")
        materialTypesPage.click_button_with_name(name = "Сохранить")
        materialTypesPage.check_error_empty_fields()
    }

    @Test
    @Story(value = "Проверка ошибки 'Пожалуйста, выберите величину объема' создании типа материала")
    fun `Проверка ошибки 'Пожалуйста, выберите величину объема' создании типа материала`() {
        loginPage.open_page()
        loginPage.check_title(title = "Авторизация")
        loginPage.field_login_form(
            RequestLogin(login = admin!!.login).login,
            RequestLogin(password = admin.password).password
        )
        loginPage.click_button_with_name(name = "Войти")
        loginPage.check_login(login = RequestLogin(login = admin.login).login)
        materialTypesPage.click_element_with_text("Типы материалов")
        materialTypesPage.check_title(title = "Типы материалов")
        materialTypesPage.click_add_button()
        materialTypesPage.check_dialog_title(title = "Создание типа материала")
        materialTypesPage.field_name(label = "Название", text = "test")
        materialTypesPage.click_button_with_name(name = "Сохранить")
        materialTypesPage.check_error_empty_field(message = "Пожалуйста, выберите величину объема")
    }

    @Test
    @Story(value = "Проверка ошибки 'Пожалуйста, укажите название типа материала' создании типа материала")
    fun `Проверка ошибки 'Пожалуйста, укажите название типа материала' создании типа материала`() {
        loginPage.open_page()
        loginPage.check_title(title = "Авторизация")
        loginPage.field_login_form(
            RequestLogin(login = admin!!.login).login,
            RequestLogin(password = admin.password).password
        )
        loginPage.click_button_with_name(name = "Войти")
        loginPage.check_login(login = RequestLogin(login = admin.login).login)
        materialTypesPage.click_element_with_text("Типы материалов")
        materialTypesPage.check_title(title = "Типы материалов")
        materialTypesPage.click_add_button()
        materialTypesPage.check_dialog_title(title = "Создание типа материала")
        materialTypesPage.choose_selector(label = "Величина объема")
        materialTypesPage.click_button_with_name(name = "Сохранить")
        materialTypesPage.check_error_empty_field(message = "Пожалуйста, укажите название типа материала")
    }

    @AfterAll
    fun after() {
        Helpers().removeAdmin(admin!!.id)
    }
}

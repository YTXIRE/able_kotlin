package com.able.tests

import com.able.config.Config
import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.logevents.SelenideLogger
import io.qameta.allure.Step
import io.qameta.allure.selenide.AllureSelenide
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.AfterEach
import org.openqa.selenium.remote.DesiredCapabilities

open class ConfigTest : Config() {

    @BeforeEach
    internal fun setUp() {
        if (this.mode == "selenoid") {
            Configuration.driverManagerEnabled = false
            Configuration.remote = this.selenoidUrl
            val capabilities = DesiredCapabilities()
            capabilities.browserName = this.browser
            capabilities.version = this.selenoidBrowserVersion
            capabilities.setCapability("enableVNC", this.selenoidEnableVNC)
            capabilities.setCapability("enableVideo", this.selenoidEnableVideo)
            capabilities.setCapability("enableLog", this.selenoidEnableLog)
            Configuration.browserCapabilities = capabilities
        }
        Configuration.baseUrl = this.baseUrl
        Configuration.browserSize = this.browserSize
        Configuration.browser = this.browser
        Configuration.timeout = this.timeout.toLong()
        SelenideLogger.addListener(
            "allure",
            AllureSelenide().screenshots(true).savePageSource(true)
        )
    }

    @AfterEach
    @Step("Закрытие браузера")
    fun teardown() {
        Selenide.closeWebDriver()
    }
}
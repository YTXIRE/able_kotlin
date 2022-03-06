package com.able.api

import com.able.api.data.RequestLogin
import com.able.api.data.ResponseDataLogin
import com.able.api.spec.Api
import com.able.config.Config
import io.restassured.RestAssured.given

open class Helpers: Config() {
    fun getAdminData(): ResponseDataLogin? {
        Api.request(this.apiUrl)?.let { Api.response()?.let { it1 -> Api.init(it, it1) } }
        return given()
            .body(RequestLogin())
            .`when`()
            .post("/auth/login")
            .then()
            .extract()
            .jsonPath()
            .getObject("data", ResponseDataLogin::class.java)
    }
}
package com.able.utils

import com.able.api.Helpers
import com.able.api.data.RequestCreateUser
import com.able.api.data.RequestDeleteUser
import com.able.api.data.ResponseDataCreateUser
import com.able.api.spec.Api
import com.able.api.utils.ResponseWrapper
import com.able.data.Faker
import com.able.model.RightsActions
import io.restassured.RestAssured.given


class Helpers : Helpers() {
    private val admin = getAdminData()

    fun createAdmin(): ResponseWrapper? {
        Api.request(this.apiUrl)?.let { Api.response(201)?.let { it1 -> Api.init(it, it1) } }
        val data = Faker.getEmail()
        if (admin != null) {
            val user = given()
                .body(
                    RequestCreateUser(
                        email = data,
                        login = data,
                        password = data,
                        token = admin.token,
                        user_id = admin.id
                    )
                )
                .`when`()
                .post("/users/create")
                .then()
                .extract()
                .jsonPath()
                .getObject("data", ResponseDataCreateUser::class.java)
            RightsActions.add_admin_rights(user_id = user.id)
            return ResponseWrapper(
                id = user.id,
                login = data,
                password = data
            )
        }
        return null
    }

    fun removeAdmin(id: Int) {
        Api.request(this.apiUrl)?.let { Api.response(200)?.let { it1 -> Api.init(it, it1) } }
        RightsActions.delete_rights_admin(user_id = id)
        given()
            .body(
                RequestDeleteUser(
                    id = id,
                    token = admin!!.token,
                    user_id = admin.id
                )
            )
            .`when`()
            .delete("/users/delete")
            .then()
            .extract()
    }
}
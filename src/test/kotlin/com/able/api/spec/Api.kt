package com.able.api.spec

import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.builder.ResponseSpecBuilder
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification
import io.restassured.specification.ResponseSpecification

class Api {
    companion object {
        fun request(url: String): RequestSpecification? {
            return RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .build()
        }

        fun response(code: Int = 200): ResponseSpecification? {
            return ResponseSpecBuilder()
                .expectStatusCode(code)
                .expectContentType(ContentType.JSON)
                .build()
        }

        fun init(request: RequestSpecification, response: ResponseSpecification) {
            RestAssured.requestSpecification = request
            RestAssured.responseSpecification = response
        }
    }
}
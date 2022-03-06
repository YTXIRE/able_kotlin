package com.able.api.data

data class RequestCreateUser(
    val email: String,
    val login: String,
    val password: String,
    val token: String,
    val user_id: Int
)
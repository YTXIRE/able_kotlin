package com.able.api.data

data class RequestDeleteUser(
    val id: Int,
    val token: String,
    val user_id: Int
)
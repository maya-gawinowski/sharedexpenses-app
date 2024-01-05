package com.example.sharedexpensesapp.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val token: String?,
    val message: String?,
    val userId: String?
)

package com.example.sharedexpensesapp.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val name: String,
    val id: String,
)
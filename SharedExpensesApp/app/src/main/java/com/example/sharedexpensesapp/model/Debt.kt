package com.example.sharedexpensesapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Debt(
    val debtorId: String,
    val creditorId: String,
    val amount: Int
)

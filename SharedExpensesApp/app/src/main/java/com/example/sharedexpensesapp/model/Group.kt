package com.example.sharedexpensesapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Group(
    val id: String,
    val name: String,
    val description: String,
    val currency: String,
    val balance: Double,
    val users: List<User>,
    val debts: List<Debt>,
    val expenses: List<Expense>
)

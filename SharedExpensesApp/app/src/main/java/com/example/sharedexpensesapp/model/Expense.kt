package com.example.sharedexpensesapp.model

data class Expense(
    val payerId: String,
    val participantsId: List<String>,
    val amount: Int,
    val description: String = ""
)

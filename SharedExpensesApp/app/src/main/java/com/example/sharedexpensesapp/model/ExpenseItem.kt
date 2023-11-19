package com.example.sharedexpensesapp.model

data class ExpenseItem(
    val payerId: String,
    val amount: Double,
    val description: String,
    val date: String
)

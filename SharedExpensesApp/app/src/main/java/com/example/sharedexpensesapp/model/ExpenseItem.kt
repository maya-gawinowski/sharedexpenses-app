package com.example.sharedexpensesapp.model

data class ExpenseItem(
    open val participant: String,
    open val amount: Double,
    open val description: String,
    open val date: String
)

package com.example.sharedexpensesapp.model

data class GroupItem(
    val name: String,
    val description: String,
    val balance: Double,
    val participants: List<String>,
    val expenses: List<ExpenseItem>,
    val currency: String,
)

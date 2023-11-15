package com.example.sharedexpensesapp.model

import java.text.NumberFormat

data class GroupItem(
    val name: String,
    val description: String,
    val balance: Double,
    val participants: List<Pair<String,Double>>,
    val expenses: List<ExpenseItem>,
    val currency: String,
)
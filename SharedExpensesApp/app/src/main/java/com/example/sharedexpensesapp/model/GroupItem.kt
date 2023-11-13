package com.example.sharedexpensesapp.model

import java.text.NumberFormat

data class GroupItem(
    open val name: String,
    open val description: String,
    open val balance: Double,
    open val participants: List<Pair<String,Double>>,
    open val expenses: List<ExpenseItem>,
    open val currency: String,
)
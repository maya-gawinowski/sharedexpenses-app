package com.example.sharedexpensesapp.model

import java.text.NumberFormat

sealed class GroupItem(
    open val name: String,
    open val description: String,
    open val balance: Double,
    open val participants: List<Pair<String,Double>>,
    open val expenses: List<ExpenseItem>,
    open val currency: String,
) {
    data class GroupI(
        override val name: String,
        override val description: String,
        override val balance: Double,
        override val participants: List<Pair<String,Double>>,
        override val expenses: List<ExpenseItem>,
        override val currency: String,
    ) : GroupItem(name, description, balance, participants,expenses, currency)
}
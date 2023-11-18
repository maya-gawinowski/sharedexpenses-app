package com.example.sharedexpensesapp.utils

import com.example.sharedexpensesapp.model.Expense
import com.example.sharedexpensesapp.model.ExpenseItem
import com.example.sharedexpensesapp.model.Group
import com.example.sharedexpensesapp.model.GroupItem
import com.example.sharedexpensesapp.model.User

class GroupMapper {
    companion object {
        fun mapToGroupItem(group: Group): GroupItem {
            return GroupItem(
                name = group.name,
                description = group.description,
                balance = group.balance,
                participants = group.users.map { user: User -> user.name },
                expenses = group.expenses.map { expense: Expense -> mapToExpenseItem(expense) },
                currency = group.currency
            )
        }

        private fun mapToExpenseItem(expense: Expense): ExpenseItem {
            return ExpenseItem(
                participant = expense.payerId,
                amount = expense.amount,
                description = expense.description,
                date = expense.date.slice(0..9)
            )
        }
    }
}
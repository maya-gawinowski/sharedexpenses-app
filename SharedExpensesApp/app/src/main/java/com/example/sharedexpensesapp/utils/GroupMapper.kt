package com.example.sharedexpensesapp.utils

import com.example.sharedexpensesapp.model.Expense
import com.example.sharedexpensesapp.model.ExpenseItem
import com.example.sharedexpensesapp.model.Group
import com.example.sharedexpensesapp.model.GroupItem

class GroupMapper {
    companion object {
        fun mapToGroupItem(group: Group): GroupItem {
            return GroupItem(
                id = group.id,
                name = group.name,
                description = group.description,
                balance = group.balance,
                currency = group.currency
            )
        }

        fun mapToExpenseItem(expense: Expense): ExpenseItem {
            return ExpenseItem(
                payerId = expense.payerId,
                amount = expense.amount,
                description = expense.description,
                date = expense.date.slice(0..9)
            )
        }
    }
}
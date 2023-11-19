package com.example.sharedexpensesapp.datasource

import com.example.sharedexpensesapp.model.Expense

interface ExpensesCallback {
    fun onSuccess(expenses: List<Expense>)
    fun onFailure(error: String)
}
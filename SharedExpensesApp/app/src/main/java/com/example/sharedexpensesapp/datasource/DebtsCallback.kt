package com.example.sharedexpensesapp.datasource

import com.example.sharedexpensesapp.model.Debt

interface DebtsCallback {
    fun onSuccess(debts: List<Debt>)
    fun onFailure(error: String)
}
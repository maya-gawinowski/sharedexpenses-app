package com.example.sharedexpensesapp.datasource

interface UserIdCallback {
    fun onSuccess(userId: String)
    fun onFailure(errorMessage: String)
}
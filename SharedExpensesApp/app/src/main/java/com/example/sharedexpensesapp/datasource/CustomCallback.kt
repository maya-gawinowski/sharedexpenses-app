package com.example.sharedexpensesapp.datasource

interface CustomCallback<SuccessType> {
    fun onSuccess(success: SuccessType)
    fun onFailure(error: String)
}
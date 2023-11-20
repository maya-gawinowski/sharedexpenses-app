package com.example.sharedexpensesapp.datasource

import com.example.sharedexpensesapp.model.User

interface UsersCallback {
    fun onSuccess(users: List<User>)
    fun onFailure(error: String)
}
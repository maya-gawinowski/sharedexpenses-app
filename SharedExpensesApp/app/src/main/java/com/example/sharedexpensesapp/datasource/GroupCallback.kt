package com.example.sharedexpensesapp.datasource

import com.example.sharedexpensesapp.model.Group

interface GroupCallback {
    fun onSuccess(group: Group)
    fun onFailure(error: String)
}
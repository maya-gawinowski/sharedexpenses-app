package com.example.sharedexpensesapp.datasource

import com.example.sharedexpensesapp.model.Group

interface GroupsCallback {
    fun onSuccess(groups: List<Group>)
    fun onFailure(error: String)
}
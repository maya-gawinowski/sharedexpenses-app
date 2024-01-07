package com.example.sharedexpensesapp.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sharedexpensesapp.datasource.CustomCallback
import com.example.sharedexpensesapp.datasource.RestClient
import com.example.sharedexpensesapp.model.Group

class GroupViewModel : ViewModel() {
    private val _group = mutableStateOf<Group?>(null)

    val group get() = _group.value

    fun fetchGroup(groupId: String) {
        RestClient.getGroupById(object : CustomCallback<Group> {
            override fun onSuccess(group: Group) {
                _group.value = group
                Log.d("RestClient", "GET group success $_group")
            }

            override fun onFailure(error: String) {
                Log.d("RestClient", "GET group error $error")
            }
        }, groupId)
    }

}
package com.example.sharedexpensesapp.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sharedexpensesapp.datasource.CustomCallback
import com.example.sharedexpensesapp.datasource.RestClient
import com.example.sharedexpensesapp.model.Debt
import com.example.sharedexpensesapp.model.GroupItem
import com.example.sharedexpensesapp.model.User

class BalanceViewModel : ViewModel() {
    private val _receivedDebts = mutableStateOf(emptyList<Debt>())
    private val _receivedUsers = mutableStateOf(emptyList<User>())

    val debts get() = _receivedDebts.value
    val users get() = _receivedUsers.value

    fun fetchDebts(group: GroupItem) {
        RestClient.getDebts(object : CustomCallback<List<Debt>> {
            override fun onSuccess(debts: List<Debt>) {
                _receivedDebts.value = debts
                Log.d("RestClient", "GET debts success $_receivedDebts")
            }

            override fun onFailure(error: String) {
                Log.d("RestClient", "GET debts error $error")
            }
        }, group.id)
    }

    fun fetchUsers(group: GroupItem) {
        RestClient.getUsers(object : CustomCallback<List<User>> {
            override fun onSuccess(users: List<User>) {
                _receivedUsers.value = users
                Log.d("RestClient", "GET users success $_receivedUsers")
            }

            override fun onFailure(error: String) {
                Log.d("RestClient", "GET users error $error")
            }
        }, group.id)
    }
}
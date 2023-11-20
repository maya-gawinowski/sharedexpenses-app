package com.example.sharedexpensesapp.ui.screens

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.sharedexpensesapp.datasource.RestClient
import com.example.sharedexpensesapp.datasource.UsersCallback
import com.example.sharedexpensesapp.model.User

class AddExpenseViewModel : ViewModel() {
    private val _participants = mutableStateOf(emptyList<ExpenseParticipant>())
    val participants: State<List<ExpenseParticipant>>
        get() = _participants

    fun toggleParticipantSelected(participant: ExpenseParticipant) =
        participants.value.find { it.userId == participant.userId }?.let { p ->
            p.selected = !p.selected
        }

    fun fetchUsers(groupId: String) {
        RestClient.instance.getUsers(object : UsersCallback {
            override fun onSuccess(users: List<User>) {
                _participants.value = users.map { user: User ->
                    ExpenseParticipant(
                        userId = user.id,
                        name = user.name
                    )
                }
            }

            override fun onFailure(error: String) {
                Log.d("RestClient", "GET users error $error")
            }
        }, groupId)
    }
}

class ExpenseParticipant(
    val userId: String,
    val name: String,
    initialSelected: Boolean = true
) {
    var selected by mutableStateOf(initialSelected)
}
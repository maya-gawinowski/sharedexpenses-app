package com.example.sharedexpensesapp.ui.screens

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.sharedexpensesapp.datasource.RestClient
import com.example.sharedexpensesapp.datasource.UsersCallback
import com.example.sharedexpensesapp.model.User
import java.time.LocalDate

class AddExpenseViewModel : ViewModel() {
    var pickedDate: LocalDate by mutableStateOf(LocalDate.now())
    var description by mutableStateOf("")
    var amountInput by mutableStateOf("")

    private val _participants = mutableStateOf(emptyList<ExpenseParticipant>())
    val participants: State<List<ExpenseParticipant>>
        get() = _participants

    private val _payerId = mutableStateOf<String?>(null)
    val payerId: MutableState<String?>
        get() = _payerId

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

    fun saveExpense(groupId: String) {
        RestClient.instance.addExpense(
            groupId = groupId,
            payerId = payerId.value!!,
            participantsIds = participants.value
                .filter { expenseParticipant -> expenseParticipant.selected }
                .map { expenseParticipant -> expenseParticipant.userId },
            amount = amountInput.toDouble() / 100,
            date = pickedDate.toString(),
            description = description
        )
    }
}

class ExpenseParticipant(
    val userId: String,
    val name: String,
    initialSelected: Boolean = true
) {
    var selected by mutableStateOf(initialSelected)
}
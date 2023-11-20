package com.example.sharedexpensesapp.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class AddExpenseViewModel : ViewModel() {
    private val _participants = getParticipants().toMutableStateList()
    val participants: List<ExpenseParticipant>
        get() = _participants

    fun toggleParticipantSelected(participant: ExpenseParticipant) =
        participants.find { it.userId == participant.userId }?.let { p ->
            p.selected = !p.selected
        }
}

class ExpenseParticipant(
    val userId: String,
    val name: String,
    initialSelected: Boolean = true
) {
    var selected by mutableStateOf(initialSelected)
}

private fun getParticipants() = List(10) { i -> ExpenseParticipant(i.toString(), "Marek $i") }
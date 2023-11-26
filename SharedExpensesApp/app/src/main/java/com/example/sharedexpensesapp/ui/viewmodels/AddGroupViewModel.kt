package com.example.sharedexpensesapp.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sharedexpensesapp.datasource.RestClient
import kotlinx.coroutines.launch
import com.example.sharedexpensesapp.datasource.UsersCallback
import com.example.sharedexpensesapp.model.User
import androidx.compose.runtime.State
import com.example.sharedexpensesapp.datasource.UserIdCallback
class AddGroupViewModel : ViewModel() {
    var groupName = mutableStateOf("")
    var groupDescription = mutableStateOf("")
    var currency = mutableStateOf("EUR")
    private val _participants = mutableStateOf(listOf(GroupParticipant(userId = "1", name = "You")))
    val participants: State<List<GroupParticipant>> = _participants

    var creationComplete = mutableStateOf(false)

    fun addParticipantByName(name: String) {
        if (name == "You") {
            _participants.value = _participants.value + GroupParticipant(userId = "1", name = name)
        } else {
            RestClient.instance.getUserIdByName(name, object : UserIdCallback {
                override fun onSuccess(userId: String) {
                    val newParticipant = GroupParticipant(userId = userId, name = name)
                    _participants.value = _participants.value + newParticipant
                }

                override fun onFailure(errorMessage: String) {
                    Log.e("AddGroupViewModel", "Error fetching user ID: $errorMessage")
                }
            })
        }
    }

    fun createGroup(
        groupName: String,
        currency: String,
        groupDescription: String
    ) {
        val allUserIds = _participants.value.map { it.userId }
        viewModelScope.launch {
            RestClient.instance.createGroups(
                userIds = allUserIds,
                name = groupName,
                currency = currency,
                description = groupDescription
            )
            creationComplete.value = true
        }
    }
}

data class GroupParticipant(
    val userId: String,
    val name: String,
    var selected: Boolean = false
)

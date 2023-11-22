package com.example.sharedexpensesapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.sharedexpensesapp.model.GroupUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GroupViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(GroupUIState())
    val uiState: StateFlow<GroupUIState> = _uiState.asStateFlow()

    fun updateGroupSelection(group: Int) {
        val previousGroupSelected = _uiState.value.groupNumber
        updateItem(group, previousGroupSelected)
    }

    private fun updateItem(group: Int, previousGroup: Int?) {
        _uiState.update { currentState ->
            currentState.copy(
                groupNumber = group
            )
        }
    }
}
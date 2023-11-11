package com.example.sharedexpensesapp.ui.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sharedexpensesapp.model.GroupItem

class GroupViewModel private constructor() : ViewModel() {
    private val _selectedGroup = MutableLiveData<GroupItem>()
    val selectedGroup: LiveData<GroupItem> get() = _selectedGroup

    fun setSelectedGroup(group: GroupItem) {
        _selectedGroup.value = group
    }

    companion object {
        val instance: GroupViewModel = GroupViewModel()
    }

}
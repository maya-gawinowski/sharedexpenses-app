package com.example.sharedexpensesapp.ui.screens

import androidx.lifecycle.ViewModel
import com.example.sharedexpensesapp.datasource.DataSource
import com.example.sharedexpensesapp.model.GroupItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GroupViewModel : ViewModel() {
    private val _groups: MutableStateFlow<List<GroupItem>> = MutableStateFlow(DataSource.groups)
    private val _selectedGroup: MutableStateFlow<GroupItem> =
        MutableStateFlow(GroupItem("No group selected", ""))
    val groups: StateFlow<List<GroupItem>> get() = _groups
    val selectedGroup: StateFlow<GroupItem> get() = _selectedGroup


    fun setGroups(groups: List<GroupItem>) {
        _groups.value = groups
    }

    fun setSelectedGroup(group: GroupItem) {
        _selectedGroup.value = group
    }

    fun setSelectedGroup(groupName: String) {
        _selectedGroup.value = getByName(groupName)
    }

    fun getByName(name: String) =
        _groups.value.find { groupItem -> groupItem.name == name } ?: GroupItem(
            "Group not found",
            ""
        )
}
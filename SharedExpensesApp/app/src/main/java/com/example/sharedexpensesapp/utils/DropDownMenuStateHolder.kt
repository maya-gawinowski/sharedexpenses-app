package com.example.sharedexpensesapp.utils

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class DropDownMenuStateHolder(val items: List<String>) {

    var expanded by mutableStateOf(false)
    var value by mutableStateOf("")
    private var selectedIndex by mutableIntStateOf(-1)

    fun select(newValue: Int) {
        selectedIndex = newValue
        value = items[selectedIndex]
        expanded = false
    }
}

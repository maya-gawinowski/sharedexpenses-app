package com.example.sharedexpensesapp.model

import java.text.NumberFormat

sealed class GroupItem(
    open val name: String,
    open val description: String,
) {
    data class GroupI(
        override val name: String,
        override val description: String,
    ) : GroupItem(name, description)
}
package com.example.sharedexpensesapp.datasource

import com.example.sharedexpensesapp.model.GroupItem

object DataSource {
    val groups = listOf(
        GroupItem(
            id = "0",
            name = "Default group",
            description = "Description 1",
            balance = 123.4,
            currency = "€"
        )
    )
}

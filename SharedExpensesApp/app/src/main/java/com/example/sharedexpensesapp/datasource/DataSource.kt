package com.example.sharedexpensesapp.datasource

import com.example.sharedexpensesapp.model.GroupItem

object DataSource {
    val groups = listOf(
        GroupItem.GroupI(
            name = "Group 1",
            description = "Description 1",
        ),
        GroupItem.GroupI(
            name = "Group 2",
            description = "Description 2",
        ),
        GroupItem.GroupI(
            name = "Group 3",
            description = "Description 3",
        ),
    )
}
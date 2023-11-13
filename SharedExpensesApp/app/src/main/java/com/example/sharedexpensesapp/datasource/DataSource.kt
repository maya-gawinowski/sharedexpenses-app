package com.example.sharedexpensesapp.datasource

import com.example.sharedexpensesapp.model.GroupItem
import com.example.sharedexpensesapp.model.User

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
        GroupItem.GroupI(
            name = "Group 3",
            description = "Description 3",
        ),GroupItem.GroupI(
            name = "Group 3",
            description = "Description 3",
        ),GroupItem.GroupI(
            name = "Group 3",
            description = "Description 3",
        ),GroupItem.GroupI(
            name = "Group 3",
            description = "Description 3",
        ),GroupItem.GroupI(
            name = "Group 3",
            description = "Description 3",
        ),GroupItem.GroupI(
            name = "Group 3",
            description = "Description 3",
        ),GroupItem.GroupI(
            name = "Group 3",
            description = "Description 3",
        ),
    )
    val users = listOf(
        User(
            name = "Mitsch",
            id = "1",
        ),
        User(
            name = "Maya",
            id = "2",
        ),
        User(
            name = "Manca",
            id = "3",
        ),
        User(
            name = "Kiran",
            id = "4",
        ),
        User(
            name = "Marcin",
            id = "5",
        ),
        User(
            name = "Luca",
            id = "6",
        ),
        User(
            name = "Eckelman",
            id = "7",
        ),
    )
}
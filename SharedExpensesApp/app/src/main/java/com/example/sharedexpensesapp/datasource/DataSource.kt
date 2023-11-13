package com.example.sharedexpensesapp.datasource

import com.example.sharedexpensesapp.model.ExpenseItem
import com.example.sharedexpensesapp.model.GroupItem
import com.example.sharedexpensesapp.model.User

object DataSource {
    val groups = listOf(
        GroupItem.GroupI(
            name = "Group 1",
            description = "Description 1",
            balance = 123.4,
            participants = listOf(
                Pair("User 1", 3.14),
                Pair("User 2", 2.5),
                Pair("User 3", 1.0)
            ),
            expenses = listOf(
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "Description",
                    date = "23/12/2023"
                ),
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "Description",
                    date = "23/12/2023"
                ),
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "Description",
                    date = "23/12/2023"
                ),
            ),
            currency = "€"
        ),
        GroupItem.GroupI(
            name = "Group 2",
            description = "Description 2",
            balance = 123.4,
            participants = listOf(
                Pair("User 1", 3.14),
                Pair("User 2", 2.5),
                Pair("User 3", 1.0)
            ),
            expenses = listOf(
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "Description",
                    date = "23/12/2023"
                ),
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "Description",
                    date = "23/12/2023"
                ),
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "Description",
                    date = "23/12/2023"
                ),
            ),
            currency = "€"

        ),
        GroupItem.GroupI(
            name = "Group 3",
            description = "Description 3",
            balance = 123.4,
            participants = listOf(
                Pair("User 1", 3.14),
                Pair("User 2", 2.5),
                Pair("User 3", 1.0)
            ),
            expenses = listOf(
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "Description",
                    date = "23/12/2023"
                ),
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "Description",
                    date = "23/12/2023"
                ),
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "Description",
                    date = "23/12/2023"
                ),
            ),
            currency = "€"

        ),
        GroupItem.GroupI(
            name = "Group 3",
            description = "Description 3",
            balance = 123.4,
            participants = listOf(
                Pair("User 1", 3.14),
                Pair("User 2", 2.5),
                Pair("User 3", 1.0)
            ),
            expenses = listOf(
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "Description",
                    date = "23/12/2023"
                ),
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "Description",
                    date = "23/12/2023"
                ),
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "Description",
                    date = "23/12/2023"
                ),
            ),
            currency = "€"
        ),GroupItem.GroupI(
            name = "Group 3",
            description = "Description 3",
            balance = 123.4,
            participants = listOf(
                Pair("User 1", 3.14),
                Pair("User 2", 2.5),
                Pair("User 3", 1.0)
            ),
            expenses = listOf(
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "Description",
                    date = "23/12/2023"
                ),
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "Description",
                    date = "23/12/2023"
                ),
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "Description",
                    date = "23/12/2023"
                ),
            ),
            currency = "€"
        ),GroupItem.GroupI(
            name = "Group 3",
            description = "Description 3",
            balance = 123.4,
            participants = listOf(
                Pair("User 1", 3.14),
                Pair("User 2", 2.5),
                Pair("User 3", 1.0)
            ),
            expenses = listOf(
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "Description",
                    date = "23/12/2023"
                ),
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "Description",
                    date = "23/12/2023"
                ),
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "Description",
                    date = "23/12/2023"
                ),
            ),
            currency = "€"
        ),GroupItem.GroupI(
            name = "Group 3",
            description = "Description 3",
            balance = 123.4,
            participants = listOf(
                Pair("User 1", 3.14),
                Pair("User 2", 2.5),
                Pair("User 3", 1.0)
            ),
            expenses = listOf(
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "Description",
                    date = "23/12/2023"
                ),
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "Description",
                    date = "23/12/2023"
                ),
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "Description",
                    date = "23/12/2023"
                ),
            ),
            currency = "€"
        ),GroupItem.GroupI(
            name = "Group 3",
            description = "Description 3",
            balance = 123.4,
            participants = listOf(
                Pair("User 1", 3.14),
                Pair("User 2", 2.5),
                Pair("User 3", 1.0)
            ),
            expenses = listOf(
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "Description",
                    date = "23/12/2023"
                ),
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "Description",
                    date = "23/12/2023"
                ),
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "Description",
                    date = "23/12/2023"
                ),
            ),
            currency = "€"
        ),GroupItem.GroupI(
            name = "Group 3",
            description = "Description 3",
            balance = 123.4,
            participants = listOf(
                Pair("User 1", 3.14),
                Pair("User 2", 2.5),
                Pair("User 3", 1.0)
            ),
            expenses = listOf(
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "Description",
                    date = "23/12/2023"
                ),
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "Description",
                    date = "23/12/2023"
                ),
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "Description",
                    date = "23/12/2023"
                ),
            ),
            currency = "€"
        ),GroupItem.GroupI(
            name = "Group 3",
            description = "Description 3",
            balance = 123.4,
            participants = listOf(
                Pair("User 1", 3.14),
                Pair("User 2", 2.5),
                Pair("User 3", 1.0)
            ),
            expenses = listOf(
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "Description",
                    date = "23/12/2023"
                ),
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "Description",
                    date = "23/12/2023"
                ),
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "Description",
                    date = "23/12/2023"
                ),
            ),
            currency = "€"
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
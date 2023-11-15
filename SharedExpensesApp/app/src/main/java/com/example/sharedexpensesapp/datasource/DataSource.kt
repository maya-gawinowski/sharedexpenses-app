package com.example.sharedexpensesapp.datasource

import com.example.sharedexpensesapp.model.ExpenseItem
import com.example.sharedexpensesapp.model.GroupItem
import com.example.sharedexpensesapp.model.User

object DataSource {
    val groups = listOf(
        GroupItem(
            name = "Group Choufleur",
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
                    description = "aaa",
                    date = "23/12/2023"
                ),
                ExpenseItem(
                    participant = "User 2",
                    amount = 124.56,
                    description = "bbb",
                    date = "23/12/2023"
                ),
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "ttt",
                    date = "23/12/2023"
                ),
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "ttt",
                    date = "23/12/2023"
                ),
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "ttt",
                    date = "23/12/2023"
                ),
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "ttt",
                    date = "23/12/2023"
                ),
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "ttt",
                    date = "23/12/2023"
                ),
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "ttt",
                    date = "23/12/2023"
                ),
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "ttt",
                    date = "23/12/2023"
                ),
            ),
            currency = "€"
        ),
        GroupItem(
            name = "Group Carotte",
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
                    description = "vvvvv",
                    date = "23/12/2023"
                ),
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "rrrrrr",
                    date = "23/12/2023"
                ),
                ExpenseItem(
                    participant = "User 1",
                    amount = 124.56,
                    description = "uuuu",
                    date = "23/12/2023"
                ),
            ),
            currency = "€"

        ),
        GroupItem(
            name = "Group Meringue",
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
        GroupItem(
            name = "Group Souflé",
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
        ),GroupItem(
            name = "Group Eiffel",
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
        ),GroupItem(
            name = "Group MontPellier",
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
        ),GroupItem(
            name = "Group Toulouse",
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
        ),GroupItem(
            name = "Group Paris",
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
        ),GroupItem(
            name = "Group Coloc",
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
        ),GroupItem(
            name = "Group sous",
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
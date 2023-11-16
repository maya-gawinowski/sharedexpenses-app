package com.example.sharedexpensesapp.datasource

import com.example.sharedexpensesapp.model.Expense
import com.example.sharedexpensesapp.model.ExpenseItem
import com.example.sharedexpensesapp.model.GroupItem
import com.example.sharedexpensesapp.model.User
import kotlin.random.Random

val names = listOf(
    "Elena",
    "Marcus",
    "Sophie",
    "Javier",
    "Aria",
    "Nolan",
    "Layla",
    "Caleb",
    "Zara",
    "Mateo"
)

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
    var counter = 0
    val users = names.map { name -> User((counter++).toString(), name, groups.random().name) }
    val expenses = generateRandomExpenses(10)
}

val randomDescriptions = listOf(
    "Dinner at a fancy restaurant",
    "Groceries for the week",
    "Movie night expenses",
    "Gift for a friend",
    "Shopping spree",
    "Travel expenses",
    "Coffee with friends",
    "Concert tickets",
    "Utility bill",
    "Online subscription renewal"
)

fun generateRandomExpense(): Expense {
    val randomPayer = DataSource.users.random()
    val randomParticipants =
        DataSource.users.filter { it.name != randomPayer.name }.shuffled()
            .take(Random.nextInt(1, DataSource.users.size))
    val randomGroup = DataSource.groups.random()
    val randomAmount = Random.nextInt(10, 100)
    val randomDescription = randomDescriptions.random()
    return Expense(
        randomPayer.id,
        randomParticipants.map { user -> user.id },
        randomAmount,
        randomDescription,
        randomGroup.name
    )
}

fun generateRandomExpenses(numberOfExpenses: Int): List<Expense> {
    return List(numberOfExpenses) { generateRandomExpense() }
}

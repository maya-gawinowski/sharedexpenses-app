package com.example.sharedexpensesapp.datasource

import com.example.sharedexpensesapp.model.Expense
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
            name = "Group 1",
            description = "Description 1",
        ),
        GroupItem(
            name = "Group 2",
            description = "Description 2",
        ),
        GroupItem(
            name = "Group 3",
            description = "Description 3",
        ),
        GroupItem(
            name = "Group 3",
            description = "Description 3",
        ),
        GroupItem(
            name = "Group 3",
            description = "Description 3",
        ),
        GroupItem(
            name = "Group 3",
            description = "Description 3",
        ),
        GroupItem(
            name = "Group 3",
            description = "Description 3",
        ),
        GroupItem(
            name = "Group 3",
            description = "Description 3",
        ),
        GroupItem(
            name = "Group 3",
            description = "Description 3",
        ),
        GroupItem(
            name = "Group 3",
            description = "Description 3",
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

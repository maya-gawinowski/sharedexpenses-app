package com.example.sharedexpensesapp.datasource

import com.example.sharedexpensesapp.model.Debt
import com.example.sharedexpensesapp.model.ExpenseItem
import com.example.sharedexpensesapp.model.GroupItem
import com.example.sharedexpensesapp.model.User

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
            id = "1",
            name = "Group Choufleur",
            description = "Description 1",
            balance = 123.4,
            participants = listOf(
                "User 1",
                "User 2",
                "User 3",
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
            id = "2",
            name = "Group Carotte",
            description = "Description 2",
            balance = 123.4,
            participants = listOf(
                "User 1",
                "User 2",
                "User 3",
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
            id = "3",
            name = "Group Meringue",
            description = "Description 3",
            balance = 123.4,
            participants = listOf(
                "User 1",
                "User 2",
                "User 3",
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
            id = "4",
            name = "Group Souflé",
            description = "Description 3",
            balance = 123.4,
            participants = listOf(
                "User 1",
                "User 2",
                "User 3",
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
            id = "5",
            name = "Group Eiffel",
            description = "Description 3",
            balance = 123.4,
            participants = listOf(
                "User 1",
                "User 2",
                "User 3",
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
            id = "6",
            name = "Group MontPellier",
            description = "Description 3",
            balance = 123.4,
            participants = listOf(
                "User 1",
                "User 2",
                "User 3",
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
            id = "7",
            name = "Group Toulouse",
            description = "Description 3",
            balance = 123.4,
            participants = listOf(
                "User 1",
                "User 2",
                "User 3",
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
            id = "8",
            name = "Group Paris",
            description = "Description 3",
            balance = 123.4,
            participants = listOf(
                "User 1",
                "User 2",
                "User 3",
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
            id = "9",
            name = "Group Coloc",
            description = "Description 3",
            balance = 123.4,
            participants = listOf(
                "User 1",
                "User 2",
                "User 3",
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
            id = "10",
            name = "Group sous",
            description = "Description 3",
            balance = 123.4,
            participants = listOf(
                "User 1",
                "User 2",
                "User 3",
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
    private var counter = 0
    val users = names.map { name -> User("User ${counter++}", name) }

    fun getDebts(group: GroupItem?): List<Debt> {
        val userBalances = mutableMapOf<String, Double>()

        group?.expenses?.forEach { expense ->
            val amountPerUser = expense.amount / group.participants.size
            group.participants.forEach { participant ->
                if (participant != expense.participant) {
                    val creditorId = expense.participant

                    val debtKey = "$participant-$creditorId"

                    userBalances[debtKey] = (userBalances[debtKey] ?: 0.0) + amountPerUser
                }
            }
        }

        return userBalances.map { (debtKey, amount) ->
            val (debtorId, creditorId) = debtKey.split("-")
            Debt(debtorId, creditorId, amount)
        }
    }
}

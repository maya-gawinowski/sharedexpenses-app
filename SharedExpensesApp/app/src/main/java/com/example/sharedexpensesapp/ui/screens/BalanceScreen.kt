package com.example.sharedexpensesapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sharedexpensesapp.R
import com.example.sharedexpensesapp.datasource.DataSource
import com.example.sharedexpensesapp.model.Expense
import com.example.sharedexpensesapp.model.GroupItem
import com.example.sharedexpensesapp.ui.screens.composables.Dropdown

@Composable
fun BalanceScreen(
    modifier: Modifier = Modifier,
    viewModel: GroupViewModel = viewModel(key = "application")
) {
    val groups by viewModel.groups.collectAsState()
    val selectedGroup by viewModel.selectedGroup.collectAsState()
    val expensesOfGroup = DataSource.expenses.filter { expense ->
        expense.groupId == selectedGroup.name
    }

    Column(modifier = modifier) {
        Dropdown(
            selectedItem = selectedGroup.name,
            items = groups.map { group -> group.name },
            onValueChange = { viewModel.setSelectedGroup(it) },
            onItemSelected = { viewModel.setSelectedGroup(it) }
        )
        LazyColumn {
            items(expensesOfGroup) { expense ->
                ExpenseCard(expense = expense)
            }
        }

    }
}

@Composable
fun ExpenseCard(expense: Expense) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.card_orange),
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Column(modifier = Modifier.padding(5.dp)) {
            Row {
                Text(
                    text = expense.description,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.main_purple),
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(modifier = Modifier.weight(1f))
                Text("CHF ${expense.amount}.-")
            }
            val namesOfUsers =
                DataSource.users.filter { user -> expense.participantsId.contains(user.id) }
                    .map { user -> user.name }
                    .joinToString(", ")
            Text(
                text = namesOfUsers,
                style = MaterialTheme.typography.titleSmall,
            )
            val oweText = if (expense.participantsId.size > 1) "owe to" else "owes to"
            Text(text = oweText, style = MaterialTheme.typography.labelSmall)
            val payerName = DataSource.users.find { user -> user.id == expense.payerId }?.name ?: ""
            Text(text = payerName, style = MaterialTheme.typography.titleSmall)
        }
    }
}

@Preview
@Composable
fun BalancePreview() {
    val viewModel: GroupViewModel = viewModel()
    val groups by viewModel.groups.collectAsState()
    var selectedGroup = groups[0]
    var biggestNumberOfExpenses = 0

    groups.forEach { group ->
        val expenses = DataSource.expenses.filter { expense -> expense.groupId == group.name }
        val numberOfExpenses = expenses.size
        if (numberOfExpenses > biggestNumberOfExpenses) {
            biggestNumberOfExpenses = numberOfExpenses
            selectedGroup = group
        }
    }

    Column {
        Dropdown(
            selectedItem = selectedGroup.name,
            items = groups.map { group -> group.name },
            onValueChange = { viewModel.setSelectedGroup(it) },
            onItemSelected = { viewModel.setSelectedGroup(it) }
        )
        DataSource.expenses.filter { expense -> expense.groupId == selectedGroup.name }
            .map { expense -> ExpenseCard(expense = expense) }
    }
}

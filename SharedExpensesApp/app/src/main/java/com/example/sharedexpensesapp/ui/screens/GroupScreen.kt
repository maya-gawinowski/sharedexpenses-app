package com.example.sharedexpensesapp.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharedexpensesapp.R
import com.example.sharedexpensesapp.datasource.ExpensesCallback
import com.example.sharedexpensesapp.datasource.RestClient
import com.example.sharedexpensesapp.datasource.UsersCallback
import com.example.sharedexpensesapp.model.Expense
import com.example.sharedexpensesapp.model.ExpenseItem
import com.example.sharedexpensesapp.model.GroupItem
import com.example.sharedexpensesapp.model.User
import com.example.sharedexpensesapp.utils.GroupMapper

@Composable
fun GroupScreen(
    selectedGroup: GroupItem?,
    modifier: Modifier = Modifier,
    onAddExpenseButtonClicked: () -> Unit,
) {

    var receivedExpenses by remember { mutableStateOf(emptyList<Expense>()) }
    val receivedExpensesItems by remember {
        derivedStateOf {
            receivedExpenses.map { expense -> GroupMapper.mapToExpenseItem(expense) }
        }
    }
    var receivedUsers by remember { mutableStateOf(emptyList<User>()) }
    val receivedUsersMap by remember {
        derivedStateOf {
            receivedUsers.associate { it.id to it.name }
        }
    }

    LaunchedEffect(Unit) {
        RestClient.instance.getExpenses(object : ExpensesCallback {
            override fun onSuccess(expenses: List<Expense>) {
                receivedExpenses = expenses
                Log.d("RestClient", "GET expenses success $receivedExpenses")
            }

            override fun onFailure(error: String) {
                Log.d("RestClient", "GET expenses error $error")
            }
        }, selectedGroup!!.id)
    }

    LaunchedEffect(Unit) {
        RestClient.instance.getUsers(object : UsersCallback {
            override fun onSuccess(users: List<User>) {
                receivedUsers = users
                Log.d("RestClient", "GET users success $receivedUsers")
            }

            override fun onFailure(error: String) {
                Log.d("RestClient", "GET users error $error")
            }
        }, selectedGroup!!.id)
    }


    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.illustration_sans_titre_35),
            contentDescription = "image de fond",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .matchParentSize()
        )
    }
    Column(
        modifier = modifier,
    ) {
        ExpenseBar(selectedGroup = selectedGroup, modifier = modifier.fillMaxWidth())
        LazyColumn(modifier = Modifier.weight(50f)) {
            items(receivedExpensesItems) { expenseItem ->
                ExpenseCard(
                    expense = expenseItem,
                    selectedGroup = selectedGroup,
                    users = receivedUsersMap
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Row {
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = onAddExpenseButtonClicked,
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.main_orange),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .padding(10.dp)

            ) {
                Text(
                    text = "Add new",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(10.dp)
                )
            }
        }
    }
}

@Composable
fun ExpenseBar(
    selectedGroup: GroupItem?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(R.color.main_purple)),
    ) {
        Row(
            modifier = Modifier
                .padding(start = 15.dp, end = 15.dp, bottom = 5.dp, top = 10.dp)
        ) {
            Text(
                text = "Total expense",
                color = Color.White,
                fontSize = 25.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "${selectedGroup?.balance.toString()} ${selectedGroup?.currency}",
                color = Color.White,
                fontSize = 25.sp
            )
        }
        Row(
            modifier = Modifier
                .padding(start = 15.dp, end = 15.dp, bottom = 15.dp, top = 3.dp)
        ) {
            Text(
                text = "Spent by you",
                color = Color.White
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "${selectedGroup?.balance.toString()} ${selectedGroup?.currency}",
                color = Color.White
            )
        }
    }

}

@Composable
fun ExpenseCard(
    modifier: Modifier = Modifier,
    expense: ExpenseItem,
    selectedGroup: GroupItem?,
    users: Map<String, String>
) {
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
        Row(
            modifier = Modifier
                .padding(15.dp)
        ) {
            Column {
                Text(
                    text = expense.description,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.main_purple)
                )
                Text(
                    text = "Payed by ${users[expense.payerId]}",
                    color = colorResource(R.color.main_purple)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Column {
                if (selectedGroup != null) {
                    Text(
                        text = "${expense.amount} ${selectedGroup.currency}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.main_orange)
                    )
                }
                Text(
                    text = expense.date,
                    color = colorResource(R.color.main_orange)
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCreateGroupScreen() {
    CreateGroupScreen()
}
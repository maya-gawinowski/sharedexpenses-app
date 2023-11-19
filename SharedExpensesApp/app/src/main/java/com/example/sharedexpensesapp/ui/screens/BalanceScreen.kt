package com.example.sharedexpensesapp.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharedexpensesapp.R
import com.example.sharedexpensesapp.datasource.DebtsCallback
import com.example.sharedexpensesapp.datasource.RestClient
import com.example.sharedexpensesapp.datasource.UsersCallback
import com.example.sharedexpensesapp.model.Debt
import com.example.sharedexpensesapp.model.GroupItem
import com.example.sharedexpensesapp.model.User

@Composable
fun BalanceScreen(
    modifier: Modifier = Modifier,
    group: GroupItem?
) {
    var receivedDebts by remember { mutableStateOf(emptyList<Debt>()) }
    var receivedUsers by remember { mutableStateOf(emptyList<User>()) }
    val receivedUsersMap by remember {
        derivedStateOf {
            receivedUsers.associate { it.id to it.name }
        }
    }

    LaunchedEffect(Unit) {
        RestClient.instance.getDebts(object : DebtsCallback {
            override fun onSuccess(debts: List<Debt>) {
                receivedDebts = debts
                Log.d("RestClient", "GET debts success $receivedDebts")
            }

            override fun onFailure(error: String) {
                Log.d("RestClient", "GET debts error $error")
            }
        }, group!!.id)
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
        }, group!!.id)
    }


    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.illustration_sans_titre_35),
            contentDescription = "Hintergrundbild",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
    }
    Column(modifier = modifier) {
        LazyColumn {
            items(receivedDebts) { debt -> DebtCard(debt = debt, group = group, users = receivedUsersMap) }
        }
    }
}

@Composable
fun DebtCard(debt: Debt, group: GroupItem?, users: Map<String, String>) {
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
                    text = "${users[debt.debtorId]}",
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.main_purple),
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    "${debt.amount} ${group?.currency}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.main_orange)
                )
            }
            Text(
                text = "owes to",
                color = colorResource(R.color.main_purple),
                fontSize = 13.sp
            )
            Text(
                text = "${ users[debt.creditorId] }",
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.main_purple),
                fontSize = 20.sp
            )
        }
    }
}

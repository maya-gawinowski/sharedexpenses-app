package com.example.sharedexpensesapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sharedexpensesapp.R
import com.example.sharedexpensesapp.model.Debt
import com.example.sharedexpensesapp.model.GroupItem
import com.example.sharedexpensesapp.ui.viewmodels.BalanceViewModel

@Composable
fun BalanceScreen(
    modifier: Modifier = Modifier,
    group: GroupItem?,
    balanceViewModel: BalanceViewModel = viewModel()
) {
    val receivedUsersMap by remember {
        derivedStateOf {
            balanceViewModel.users.associate { it.id to it.name }
        }
    }

    LaunchedEffect(Unit) {
        balanceViewModel.fetchDebts(group!!)
        balanceViewModel.fetchUsers(group)
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
            items(balanceViewModel.debts) { debt -> DebtCard(debt = debt, group = group, users = receivedUsersMap) }
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
                    "${String.format("%.2f", debt.amount)} ${group?.currency}",
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
                text = "${users[debt.creditorId]}",
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.main_purple),
                fontSize = 20.sp
            )
        }
    }
}

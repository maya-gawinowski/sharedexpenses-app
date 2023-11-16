package com.example.sharedexpensesapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharedexpensesapp.R
import com.example.sharedexpensesapp.datasource.DataSource
import com.example.sharedexpensesapp.model.Debt
import com.example.sharedexpensesapp.model.GroupItem

@Composable
fun BalanceScreen(
    modifier: Modifier = Modifier,
    group: GroupItem?
) {
    val debts = DataSource.getDebts(group)

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
            items(debts) { debt -> DebtCard(debt = debt, group = group) }
        }
    }
}

@Composable
fun DebtCard(debt: Debt, group: GroupItem?) {
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
                    text = debt.debtorId,
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
                text = debt.creditorId,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.main_purple),
                fontSize = 20.sp
            )
        }
    }
}

@Preview
@Composable
fun BalancePreview() {
    BalanceScreen(group = DataSource.groups[0], modifier = Modifier.fillMaxSize())
}


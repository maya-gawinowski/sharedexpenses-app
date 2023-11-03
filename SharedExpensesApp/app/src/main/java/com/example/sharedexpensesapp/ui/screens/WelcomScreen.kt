package com.example.sharedexpensesapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sharedexpensesapp.model.GroupItem

@Composable
fun WelcomScreen(
    onStartOrderButtonClicked: () -> Unit,
    options: List<GroupItem.GroupI>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        options.forEach { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(5.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    Column {
                        Text(text = item.name)
                        Text(text = item.description)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Button(onClick = onStartOrderButtonClicked) {
                        Text(text = "click")
                    }
                }

            }
        }
    }
}
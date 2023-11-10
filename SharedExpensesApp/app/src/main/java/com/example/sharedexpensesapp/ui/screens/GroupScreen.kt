package com.example.sharedexpensesapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.sharedexpensesapp.model.GroupItem

@Composable
fun GroupScreen(
    options: List<GroupItem>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        options.forEach { item ->
            Column {
                Text(text = item.name)
                Text(text = item.description)
            }
        }
    }
}

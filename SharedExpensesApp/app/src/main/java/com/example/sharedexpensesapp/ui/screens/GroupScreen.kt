package com.example.sharedexpensesapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun GroupScreen(
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        val group = GroupViewModel.instance.selectedGroup.value
        Text(text = group?.name ?: "")
        Text(text = group?.description ?: "")
    }
}

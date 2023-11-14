package com.example.sharedexpensesapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GroupScreen(
    modifier: Modifier = Modifier,
    viewModel: GroupViewModel = viewModel(key = "application")
) {
    val selectedGroup by viewModel.selectedGroup.collectAsState()

    Column(modifier) {
        Text(text = selectedGroup.name)
        Text(text = selectedGroup.description)
    }
}

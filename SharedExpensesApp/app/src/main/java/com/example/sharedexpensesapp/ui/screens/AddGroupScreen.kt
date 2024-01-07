package com.example.sharedexpensesapp.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sharedexpensesapp.ui.viewmodels.AddGroupViewModel
import androidx.navigation.NavController
import com.example.sharedexpensesapp.datasource.RestClient
import com.example.sharedexpensesapp.model.User
import com.example.sharedexpensesapp.ui.viewmodels.GroupParticipant

@Composable
fun AddGroupScreen(navController: NavController, modifier: Modifier) {
    val addGroupViewModel: AddGroupViewModel = viewModel()
    val participants = addGroupViewModel.participants.value
    var groupName by remember { mutableStateOf(TextFieldValue("")) }
    var groupDescription by remember { mutableStateOf(TextFieldValue("")) }
    var participantName by remember { mutableStateOf("") }
    var currency by remember { mutableStateOf("EUR") }
    var showCurrencyDropdown by remember { mutableStateOf(false) }
    val currencyOptions = listOf("EUR", "USD", "DKK")

    LaunchedEffect(Unit) {
        addGroupViewModel.addParticipantByName("You")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Create a group",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Group Name Input
        BasicTextField(
            value = groupName,
            onValueChange = { groupName = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            textStyle = TextStyle(fontSize = 16.sp),
            decorationBox = { innerTextField ->
                Box(contentAlignment = Alignment.CenterStart) {
                    if (groupName.text.isEmpty()) {
                        Text("Enter the group name", color = Color.Gray)
                    }
                    innerTextField()
                }
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Group Description Input
        BasicTextField(
            value = groupDescription,
            onValueChange = { groupDescription = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            textStyle = TextStyle(fontSize = 16.sp),
            decorationBox = { innerTextField ->
                Box(contentAlignment = Alignment.CenterStart) {
                    if (groupDescription.text.isEmpty()) {
                        Text("Enter the group description", color = Color.Gray)
                    }
                    innerTextField()
                }
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Currency Dropdown
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Currency: ", fontWeight = FontWeight.Bold)
            TextButton(onClick = { showCurrencyDropdown = true }) {
                Text(currency)
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "Dropdown Arrow"
                )
            }
            DropdownMenu(
                expanded = showCurrencyDropdown,
                onDismissRequest = { showCurrencyDropdown = false }
            ) {
                currencyOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            currency = option
                            showCurrencyDropdown = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // List of Participants
        Text(
            text = "Participants",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.Start)
        )
        participants.forEach { participant: GroupParticipant ->
            Text(text = participant.name, modifier = Modifier.padding(8.dp))
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Add Participant
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = participantName,
                onValueChange = { participantName = it },
                singleLine = true,
                label = { Text("Enter a name") },
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = {
                    if (participantName.isNotBlank()) {
                        addGroupViewModel.addParticipantByName(participantName)
                        participantName = ""
                    }
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(text = "+")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Create Group Button
        Button(
            onClick = {
                addGroupViewModel.createGroup(
                    groupName = groupName.text,
                    currency = currency,
                    groupDescription = groupDescription.text
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("Create", color = Color.White)
        }

        if (addGroupViewModel.creationComplete.value) {
            LaunchedEffect(Unit) {
                navController.popBackStack()
            }
        }
    }
}

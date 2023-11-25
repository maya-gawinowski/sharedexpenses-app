package com.example.sharedexpensesapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharedexpensesapp.datasource.RestClient
import com.example.sharedexpensesapp.datasource.UserIdCallback
import androidx.navigation.NavController
import androidx.compose.runtime.LaunchedEffect

@Composable
fun AddGroupScreen(navController: NavController, modifier: Modifier) {
    var groupName by remember { mutableStateOf(TextFieldValue("")) }
    var groupDescription by remember { mutableStateOf(TextFieldValue("")) }
    var participantName by remember { mutableStateOf("") }
    var participants by remember { mutableStateOf(listOf("You")) }
    var currency by remember { mutableStateOf("EUR") }
    var showCurrencyDropdown by remember { mutableStateOf(false) }
    var creationComplete by remember { mutableStateOf(false) }
    val currencyOptions = listOf("EUR", "USD", "DKK")
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

        BasicTextField(
            value = groupName,
            onValueChange = { groupName = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp), // Assuming a standard height for Material input fields
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

        // Placeholder for currency selector, which should be replaced with an actual implementation
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

        // List of participants
        Text(
            text = "Participants",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.Start)
        )
        participants.forEach { name ->
            Text(text = name, modifier = Modifier.padding(8.dp))
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
                        participants = participants + participantName
                        participantName = ""
                    }
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(text = "+")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        val restClient = RestClient.instance
        fun getUserIdFromServer(name: String, onResult: (String) -> Unit) {
            restClient.getUserIdByName(name, object : UserIdCallback {
                override fun onSuccess(userId: String) {
                    onResult(userId)
                }

                override fun onFailure(errorMessage: String) {
                }
            })
        }
        if (creationComplete) {
            LaunchedEffect(Unit) {
                navController.popBackStack()
            }
        }
        Button(
            onClick = {
                val userIds = mutableListOf<String>()
                participants.forEach { participant ->
                    if (participant == "You") {
                        userIds.add("1")
                    } else {
                        getUserIdFromServer(participant) { userId ->
                            userIds.add(userId)
                        }
                    }
                }
                restClient.createGroups(
                    userIds = userIds,
                    name = groupName.text,
                    currency = currency,
                    description = groupDescription.text
                ){
                    creationComplete = true
                }},
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("Create", color = Color.White)
        }
    }
}
interface UserIdCallback {
    fun onSuccess(userId: String)
    fun onFailure(errorMessage: String)
}
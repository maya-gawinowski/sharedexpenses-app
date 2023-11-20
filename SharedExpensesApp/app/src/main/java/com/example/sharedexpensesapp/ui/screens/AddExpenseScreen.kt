package com.example.sharedexpensesapp.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sharedexpensesapp.R
import com.example.sharedexpensesapp.datasource.RestClient
import com.example.sharedexpensesapp.datasource.UsersCallback
import com.example.sharedexpensesapp.model.User
import com.example.sharedexpensesapp.utils.CurrencyAmountInputVisualTransformation
import com.example.sharedexpensesapp.utils.DropDownMenuStateHolder
import com.example.sharedexpensesapp.utils.ReadonlyTextField
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExpenseScreen(
    groupId: String,
    modifier: Modifier = Modifier,
    currencySymbol: String = "EUR",
    addExpenseViewModel: AddExpenseViewModel = viewModel()
) {

    var description by remember { mutableStateOf("") }
    var amountInput by remember { mutableStateOf("") }
    var pickedDate by remember { mutableStateOf(LocalDate.now()) }
    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter.ofPattern("dd-MM-yyyy")
                .format(pickedDate)
        }
    }
    val dateDialogState = rememberMaterialDialogState()
    var dropDownMenuStateHolder by remember {
        mutableStateOf(
            DropDownMenuStateHolder(emptyList())
        )

    }
    var receivedUsers by remember { mutableStateOf(emptyList<User>()) }

    LaunchedEffect(Unit) {
        RestClient.instance.getUsers(object : UsersCallback {
            override fun onSuccess(users: List<User>) {
                receivedUsers = users
                dropDownMenuStateHolder =
                    DropDownMenuStateHolder(users.map { user -> user.name })
                Log.d("RestClient", "GET users success $users")
            }

            override fun onFailure(error: String) {
                Log.d("RestClient", "GET users error $error")
            }
        }, groupId)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.illustration_sans_titre_35),
            contentDescription = "Fond image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .matchParentSize()
        )
    }
    Column(
        modifier = modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Expense description") },
            singleLine = true,
        )
        Spacer(Modifier.size(16.dp))
        OutlinedTextField(
            value = amountInput,
            onValueChange = { amountInput = it },
            label = { Text("Amount") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            visualTransformation = CurrencyAmountInputVisualTransformation(),
            suffix = { Text(currencySymbol) },
            singleLine = true,
        )
        Spacer(Modifier.size(16.dp))
        ReadonlyTextField(
            value = TextFieldValue(formattedDate),
            onValueChange = {},
            onClick = { dateDialogState.show() },
        ) {
            Text(text = "Date")
        }
        Spacer(Modifier.size(16.dp))
        // for some reason extracting this component to a function stops it from updating its state
        ExposedDropdownMenuBox(
            expanded = dropDownMenuStateHolder.expanded,
            onExpandedChange = { dropDownMenuStateHolder.expanded = it }) {
            OutlinedTextField(
                value = dropDownMenuStateHolder.value,
                onValueChange = {},
                label = { Text(text = "Payed by") },
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = dropDownMenuStateHolder.expanded)
                },
                placeholder = {
                    Text(text = "Payed by")
                },
                modifier = Modifier.menuAnchor(),
            )
            ExposedDropdownMenu(
                expanded = dropDownMenuStateHolder.expanded,
                onDismissRequest = { dropDownMenuStateHolder.expanded = false })
            {
                dropDownMenuStateHolder.items.forEachIndexed { index, s ->
                    DropdownMenuItem(
                        text = { Text(text = s) },
                        onClick = {
                            dropDownMenuStateHolder.select(index)
                        })
                }
            }
        }
        Spacer(Modifier.size(16.dp))
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth(0.75f)
        ) {
            Text(
                text = "Split among:",
                fontWeight = FontWeight.Bold,
            )
        }
        Spacer(Modifier.size(16.dp))
        ParticipantsList(
            participants = addExpenseViewModel.participants,
            onParticipantClicked = { expenseParticipant ->
                addExpenseViewModel.toggleParticipantSelected(
                    expenseParticipant
                )
            },
            modifier = Modifier
                .weight(12f),
        )
        Spacer(Modifier.size(16.dp))
        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp)
                .fillMaxWidth(0.75f)
        ) {
            Text(text = "Add expense", fontSize = 20.sp)
        }
    }

    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton(text = "Ok")
            negativeButton(text = "Cancel")
        }
    ) {
        datepicker(
            initialDate = LocalDate.now(),
            title = "Pick a date",
        ) {
            pickedDate = it
        }
    }
}

@Composable
fun ParticipantsList(
    participants: List<ExpenseParticipant>,
    onParticipantClicked: (ExpenseParticipant) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(participants) { expenseParticipant ->
            ParticipantCard(
                participant = expenseParticipant,
                checked = expenseParticipant.selected,
                onClicked = { onParticipantClicked(expenseParticipant) },
                )
        }
    }
}


@Composable
private fun ParticipantCard(
    participant: ExpenseParticipant,
    checked: Boolean,
    onClicked: () -> Unit,
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.card_orange),
        ),
        modifier = Modifier
            .fillMaxWidth(0.75f)
            .padding(5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(enabled = true, onClick = onClicked)
//                    participants1 = participants1.mapIndexed { j, item ->
//                        if (i == j) {
//                            item.copy(isSelected = !item.isSelected)
//                        } else item
//                    }

                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = participant.name)
            if (checked) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Selected",
                    tint = Color(0xff3b6e44),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

package com.example.sharedexpensesapp.ui.screens

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
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import com.example.sharedexpensesapp.ui.screens.composables.DropdownIndexed
import com.example.sharedexpensesapp.ui.viewmodels.AddExpenseViewModel
import com.example.sharedexpensesapp.ui.viewmodels.ExpenseParticipant
import com.example.sharedexpensesapp.utils.CurrencyAmountInputVisualTransformation
import com.example.sharedexpensesapp.utils.ReadonlyTextField
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun AddExpenseScreen(
    groupId: String,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    currencySymbol: String = "EUR",
    addExpenseViewModel: AddExpenseViewModel = viewModel()
) {
    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter.ofPattern("dd-MM-yyyy").format(addExpenseViewModel.pickedDate)
        }
    }
    val userNames by remember {
        derivedStateOf {
            addExpenseViewModel.participants.value.map { participant: ExpenseParticipant -> participant.name }
        }
    }
    val isExpenseValid by remember {
        derivedStateOf {
            addExpenseViewModel.payerId.value != null && addExpenseViewModel.amountInput != ""
        }
    }
    val dateDialogState = rememberMaterialDialogState()

    val customTextFieldColors = TextFieldDefaults.colors(
        unfocusedContainerColor = colorResource(R.color.card_orange),
    )

    LaunchedEffect(Unit) {
        addExpenseViewModel.fetchUsers(groupId)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.illustration_sans_titre_35),
            contentDescription = "Fond image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
    }
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = addExpenseViewModel.description,
            onValueChange = { addExpenseViewModel.description = it },
            label = { Text("Expense description") },
            singleLine = true,
            colors = customTextFieldColors
        )
        Spacer(Modifier.size(16.dp))
        TextField(
            value = addExpenseViewModel.amountInput,
            onValueChange = { addExpenseViewModel.amountInput = it },
            label = { Text("Amount") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            visualTransformation = CurrencyAmountInputVisualTransformation(),
            suffix = { Text(currencySymbol) },
            singleLine = true,
            colors = customTextFieldColors
        )
        Spacer(Modifier.size(16.dp))
        ReadonlyTextField(
            value = TextFieldValue(formattedDate),
            onValueChange = {},
            onClick = { dateDialogState.show() },
            colors = customTextFieldColors
        ) {
            Text(text = "Date")
        }
        Spacer(Modifier.size(16.dp))
        DropdownIndexed(
            label = "Payed by",
            placeholder = "Payed by",
            items = userNames,
            onValueChange = {},
            onIndexSelected = { selectedIndex ->
                val selectedPayer: ExpenseParticipant =
                    addExpenseViewModel.participants.value[selectedIndex]
                addExpenseViewModel.payerId.value = selectedPayer.userId
            },
            colors = customTextFieldColors
        )
        Spacer(Modifier.size(16.dp))
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth(0.75f)
        ) {
            Text(
                text = "Split among:",
                fontWeight = FontWeight.Bold,
            )
        }
        Spacer(Modifier.size(16.dp))
        ParticipantsList(
            participants = addExpenseViewModel.participants.value,
            onParticipantClicked = { expenseParticipant ->
                addExpenseViewModel.toggleParticipantSelected(
                    expenseParticipant
                )
            },
            modifier = Modifier.weight(12f),
        )
        Spacer(Modifier.size(16.dp))
        Button(
            onClick = {
                addExpenseViewModel.saveExpense(groupId)
                navigateBack()
            },
            enabled = isExpenseValid,
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

    MaterialDialog(dialogState = dateDialogState, buttons = {
        positiveButton(text = "Ok")
        negativeButton(text = "Cancel")
    }) {
        datepicker(
            initialDate = LocalDate.now(),
            title = "Pick a date",
        ) {
            addExpenseViewModel.pickedDate = it
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

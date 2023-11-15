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
import com.example.sharedexpensesapp.R
import com.example.sharedexpensesapp.datasource.DataSource
import com.example.sharedexpensesapp.utils.CurrencyAmountInputVisualTransformation
import com.example.sharedexpensesapp.utils.DropDownMenuStateHolder
import com.example.sharedexpensesapp.utils.ReadonlyTextField
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun AddExpenseScreen(
    modifier: Modifier = Modifier,
    currencySymbol: String = "EUR"
) {
    val users = DataSource.users
    var description by remember { mutableStateOf("") }
    var amountInput by remember { mutableStateOf("") }
    // date picker
    var pickedDate by remember { mutableStateOf(LocalDate.now()) }
    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter.ofPattern("dd-MM-yyyy")
                .format(pickedDate)
        }
    }
    val dateDialogState = rememberMaterialDialogState()
    // dropdown menu
    val dropDownMenuStateHolder = remember {
        DropDownMenuStateHolder(users.map { user -> user.name })
    }
    // lazy list
    var participants by remember {
        mutableStateOf(
            users.map {
                UserListItem(
                    title = it.name,
                    id = it.id,
                    isSelected = true,
                )
            }
        )
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
        PayerDropDownMenu(dropDownMenuStateHolder)
        Spacer(Modifier.size(16.dp))
        Row (
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth(0.75f)
        ){
            Text(
                text = "Split among:",
                fontWeight = FontWeight.Bold,
            )
        }
        Spacer(Modifier.size(16.dp))
        LazyColumn(
            modifier = Modifier
                .weight(12f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(participants.size) { i ->
                ElevatedCard (
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
                            .clickable {
                                participants = participants.mapIndexed { j, item ->
                                    if (i == j) {
                                        item.copy(isSelected = !item.isSelected)
                                    } else item
                                }
                            }
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = participants[i].title)
                        if (participants[i].isSelected) {
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
        }
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
@OptIn(ExperimentalMaterial3Api::class)
private fun PayerDropDownMenu(dropDownMenuStateHolder: DropDownMenuStateHolder) {
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
}

data class UserListItem (
    val title: String,
    val id: String,
    val isSelected: Boolean,
)

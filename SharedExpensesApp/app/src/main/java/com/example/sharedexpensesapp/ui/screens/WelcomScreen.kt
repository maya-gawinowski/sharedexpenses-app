package com.example.sharedexpensesapp.ui.screens

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.sharedexpensesapp.model.GroupItem


@Composable
fun WelcomScreen(
    onStartOrderButtonClicked: () -> Unit,
    onAddGroupButtonClicked: () -> Unit,
    onJoinGroupButtonClicked: () -> Unit,
    options: List<GroupItem.GroupI>,
    modifier: Modifier = Modifier
) {
    var openDialog = remember { mutableStateOf(false) }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        options.forEach { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(15.dp)
                ) {
                    Column {
                        Text(
                            text = item.name,
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = item.description)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Button(onClick = onStartOrderButtonClicked) {
                        Text(text = "click")
                    }
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Row {
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { openDialog.value = true },
                modifier = Modifier
                    .padding(10.dp)

            ) {
                Text(
                    text = "+",
                    fontSize = 50.sp,
                    modifier = Modifier
                        .padding(10.dp)
                )
            }
        }
    }
    when {
        openDialog.value -> {
            choiceDialog(
                onDismissRequest = { openDialog.value = false },
                onConfirmation = {
                    openDialog.value = false
                    println("Confirmation registered") // Add logic here to handle confirmation.
                },
                dialogTitle = "Alert dialog example",
                dialogText = "This is an example of an alert dialog with buttons.",
                icon = Icons.Default.Info,
                onAddGroupButtonClicked = onAddGroupButtonClicked,
                onJoinGroupButtonClicked = onJoinGroupButtonClicked
            )
        }
    }
}

@Composable
fun choiceDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    onAddGroupButtonClicked: () -> Unit,
    onJoinGroupButtonClicked: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp)
                .shadow(12.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Pick one !",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .wrapContentSize(Alignment.Center),
                    textAlign = TextAlign.Center,
                )
                Button(
                    onClick = onAddGroupButtonClicked,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp)
                ) {
                    Text(text = "Create new group")
                }
                Button(
                    onClick = onJoinGroupButtonClicked,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp)
                ) {
                    Text(text = "Join existing group")
                }
            }

        }
    }
}
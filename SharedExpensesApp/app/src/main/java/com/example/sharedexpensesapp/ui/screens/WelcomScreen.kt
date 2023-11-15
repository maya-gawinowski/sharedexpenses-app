package com.example.sharedexpensesapp.ui.screens

import android.content.ContentValues.TAG
import android.graphics.fonts.FontStyle
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.sharedexpensesapp.R
import com.example.sharedexpensesapp.datasource.GroupsCallback
import com.example.sharedexpensesapp.datasource.RestClient
import com.example.sharedexpensesapp.model.Group
import com.example.sharedexpensesapp.model.GroupItem


@Composable
fun WelcomScreen(
    onStartOrderButtonClicked: () -> Unit,
    onAddGroupButtonClicked: () -> Unit,
    onJoinGroupButtonClicked: () -> Unit,
    onGroupSelected: (GroupItem) -> Unit,
    options: List<GroupItem>,
    modifier: Modifier = Modifier
) {
    var receivedGroups by remember { mutableStateOf(emptyList<Group>()) }
    LaunchedEffect(Unit) {
        RestClient.instance.getGroups(object : GroupsCallback {
            override fun onSuccess(groups: List<Group>) {
                receivedGroups= groups
            }

            override fun onFailure(error: String) {
                Log.d("RestClient", "GET groups error $error")
            }
        })
    }
    Log.d("RestClient", "GET groups success $receivedGroups")
    var openDialog = remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.illustration_sans_titre_35),
            contentDescription = "image de fond",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .matchParentSize()
        )
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        groupList(
            options = options,
            onStartOrderButtonClicked = onStartOrderButtonClicked,
            onGroupSelected = onGroupSelected,
            modifier = Modifier.weight(12f)
        )
        Spacer(modifier = Modifier.weight(1f))
        Row {
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { openDialog.value = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.main_orange),
                    contentColor = Color.White),
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
fun groupCard(
    group: GroupItem,
    modifier: Modifier = Modifier,
    onStartOrderButtonClicked: () -> Unit,
    onGroupSelected: (GroupItem) -> Unit,
    ) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.card_orange),
        ),
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
                    text = group.name,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.main_purple)
                )
                Text(
                    text = group.description,
                    color = colorResource(R.color.main_orange)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    onStartOrderButtonClicked()
                    onGroupSelected(group)
                    Log.i("GROUP", group.name)
                }
            ) {
                Text(text = "view")
            }
        }
    }
}

@Composable
fun groupList(
    options: List<GroupItem>,
    modifier: Modifier = Modifier,
    onStartOrderButtonClicked: () -> Unit,
    onGroupSelected: (GroupItem) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        items(options) { option ->
            groupCard(
                group = option,
                modifier = modifier.padding(8.dp),
                onStartOrderButtonClicked = onStartOrderButtonClicked,
                onGroupSelected = onGroupSelected
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
            colors = CardDefaults.cardColors(
                containerColor = colorResource(R.color.main_purple),
            ),
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
                    color = Color.White,
                    modifier = Modifier
                        .wrapContentSize(Alignment.Center)
                        .padding(top = 5.dp, bottom = 10.dp),
                    textAlign = TextAlign.Center,
                )
                Button(
                    onClick = onAddGroupButtonClicked,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.button_purple),
                        contentColor = colorResource(R.color.main_purple)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp)
                ) {
                    Text(text = "Create new group")
                }
                Button(
                    onClick = onJoinGroupButtonClicked,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.button_purple),
                        contentColor = colorResource(R.color.main_purple)),
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
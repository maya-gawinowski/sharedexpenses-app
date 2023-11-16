package com.example.sharedexpensesapp.ui.screens

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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.sharedexpensesapp.R
import com.example.sharedexpensesapp.model.GroupItem

@Composable
fun CreateGroupScreen() {
    var groupName by remember { mutableStateOf(TextFieldValue("")) }
    var groupDescription by remember { mutableStateOf(TextFieldValue("")) }

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
        Text(
            text = "Currency: EUR",
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Placeholder for the participants list, which should be replaced with an actual implementation
        Text(
            text = "Participants",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.Start)
        )

        // Placeholder for the user name, which should be replaced with an actual implementation
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "You (user name)",
                modifier = Modifier.weight(1f)
            )
            // This should be replaced with an actual switch or checkbox
            Text(text = "[Toggle Switch]")
        }

        // Placeholder for the add participant input, which should be replaced with an actual implementation
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = TextFieldValue(""),
                onValueChange = {},
                modifier = Modifier.weight(1f),
                decorationBox = { innerTextField ->
                    Box(contentAlignment = Alignment.CenterStart) {
                        Text("Enter a name", color = Color.Gray)
                        innerTextField()
                    }
                }
            )
            Text(text = "+", modifier = Modifier.padding(start = 8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* Handle create group logic */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("Create", color = Color.White)
        }
    }
}


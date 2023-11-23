package com.example.sharedexpensesapp.ui.screens
import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.tooling.preview.Preview
import com.example.sharedexpensesapp.utils.createNotificationChannel
import com.example.sharedexpensesapp.utils.sendNotification
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.sharedexpensesapp.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

@Composable
fun AccountScreen() {
    var name by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var userEmail by remember { mutableStateOf("user@example.com") }
    var notificationsEnabled by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val density = LocalDensity.current.density

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(R.drawable.illustration_sans_titre_35),
            contentDescription = "image de fond",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        // Input Fields
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){


            Card(

                modifier = Modifier
                    .fillMaxWidth()
                    .size(width = 300.dp, height = 250.dp)
                    .padding(top = 16.dp),
                border= BorderStroke(5.dp, colorResource(id = R.color.main_orange)),

                backgroundColor = colorResource(id = R.color.main_purple),

            )
            {
                Column (modifier = Modifier
                    .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally){
                    Text(
                        text = "Welcome user $name\n$userEmail",

                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(16.dp),
                        style = TextStyle(fontSize = 30.sp),
                        textAlign = TextAlign.Center,
                        color = colorResource(id = R.color.main_orange),
                    )
                }

            }
            Text(
                text = "Settings",
                style = TextStyle(fontSize = 30.sp),
                textAlign = TextAlign.Center,
                color = colorResource(id = R.color.purple_700),
            )
            TextField(
                value = name,
                onValueChange = { name = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                label = { Text("Name") },

                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )
            TextField(
                value = nickname,
                onValueChange = { nickname = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                label = { Text("Nickname") },
                visualTransformation = PasswordVisualTransformation(),

                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                )
            )

            // Toggle Button for Notifications
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Receive Notifications", modifier = Modifier.alignByBaseline() ,
                        style = TextStyle(fontSize = 30.sp),
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.purple_700),)
                Spacer(modifier = Modifier.width(15.dp))
                Switch(
                    checked = notificationsEnabled,
                    onCheckedChange = { notificationsEnabled = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = colorResource(id = R.color.purple_200),
                        uncheckedThumbColor = colorResource(id = R.color.purple_500),
                        checkedTrackColor = colorResource(id = R.color.purple_700),
                        uncheckedTrackColor =colorResource(id = R.color.purple_500),
                    )
                )




            }
        }



    }

}



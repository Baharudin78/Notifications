package com.baharudin.notifications.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.baharudin.notifications.MyViewModel
import com.baharudin.notifications.navigation.Screen

@Composable
fun MainScreen(
    navController : NavHostController,
    mainViewModel : MyViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = mainViewModel::showSimpleNotification
        ) {
            Text(text = "Show Simple Notification")
        }

        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = mainViewModel::updateNotification
        ) {
            Text(text = "Update Notification")
        }

        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = mainViewModel::cancelSimpleNotification
        ) {
            Text(text = "Cancel Simple Notification")
        }

        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {
                navController.navigate(
                    Screen.Detail.passArgument(message = "Coming from main screen")
                )
            }
        ) {
            Text(text = "Detail Screen")
        }
    }

}
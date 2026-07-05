package com.example.anucogent.ui.screen.videocall

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun VideoCallScreen(

    onBack: (() -> Unit)? = null

) {

    Column(

        modifier = Modifier.fillMaxSize(),

        horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.Center

    ) {

        Text(

            text = "Video Call",

            style = MaterialTheme.typography.headlineMedium

        )

        if (onBack != null) {

            Button(

                onClick = {

                    onBack()

                }

            ) {

                Text("Back")

            }

        }

    }

}
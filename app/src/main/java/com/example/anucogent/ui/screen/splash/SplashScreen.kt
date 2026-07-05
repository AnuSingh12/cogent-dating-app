package com.example.anucogent.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.anucogent.R

@Composable
fun SplashScreen(

    navigateToLogin: () -> Unit

) {
    val viewModel: SplashViewModel = viewModel(
        factory = SplashViewModelFactory()
    )

    LaunchedEffect(Unit) {

        viewModel.startSplash {

            navigateToLogin()

        }

    }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(

                Brush.verticalGradient(

                    colors = listOf(
                        Color(0xFFFF5F6D),
                        Color(0xFFFFC371)
                    )

                )

            ),

        horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.Center

    ) {

        Image(

            painter = painterResource(id = R.drawable.ic_launcher_foreground),

            contentDescription = null

        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(

            text = "Anu Dating",

            style = MaterialTheme.typography.headlineLarge,

            color = Color.White

        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(

            text = "Find Your Perfect Match",

            fontSize = 18.sp,

            color = Color.White

        )

    }

}
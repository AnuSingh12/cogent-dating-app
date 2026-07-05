package com.example.anucogent.ui.screen.login

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.anucogent.data.repository.AuthRepo

@Composable
fun LoginScreen(
    navigateToHome: () -> Unit
) {

    val context = LocalContext.current

    val repo = AuthRepo()

    val viewModel: LoginViewModel = viewModel(
        factory = LoginViewModelFactory(repo)
    )

    var phoneNumber by remember {
        mutableStateOf("")
    }

    var otp by remember {
        mutableStateOf("")
    }

    val otpSent by viewModel.otpSent.collectAsState()

    val loginSuccess by viewModel.loginSuccess.collectAsState()

    val error by viewModel.error.collectAsState()

    LaunchedEffect(loginSuccess) {

        if (loginSuccess) {

            navigateToHome()

        }

    }

    LaunchedEffect(error) {

        if (error.isNotEmpty()) {

            Toast.makeText(
                context,
                error,
                Toast.LENGTH_SHORT
            ).show()

        }

    }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),

        horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.Center

    ) {

        Text(
            text = "Dating App",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(

            value = phoneNumber,

            onValueChange = {

                phoneNumber = it

            },

            label = {

                Text("Phone Number")

            },

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ),

            modifier = Modifier.fillMaxWidth()

        )

        Spacer(modifier = Modifier.height(15.dp))

        Button(

            onClick = {

                viewModel.sendOtp(

                    activity = context as Activity,

                    phoneNumber = "+91$phoneNumber"

                )

            },

            modifier = Modifier.fillMaxWidth()

        ) {

            Text("Send OTP")

        }

        if (otpSent) {

            Spacer(modifier = Modifier.height(25.dp))

            OutlinedTextField(

                value = otp,

                onValueChange = {

                    otp = it

                },

                label = {

                    Text("Enter OTP")

                },

                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),

                modifier = Modifier.fillMaxWidth()

            )

            Spacer(modifier = Modifier.height(15.dp))

            Button(

                onClick = {

                    viewModel.verifyOtp(otp)

                },

                modifier = Modifier.fillMaxWidth()

            ) {

                Text("Verify OTP")

            }

        }
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedButton(

            onClick = {

                viewModel.demoLogin()

            },

            modifier = Modifier.fillMaxWidth()

        ) {

            Text("Demo Login")

        }

    }

}
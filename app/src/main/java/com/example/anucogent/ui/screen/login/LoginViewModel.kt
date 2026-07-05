package com.example.anucogent.ui.screen.login

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.example.anucogent.data.repository.AuthRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel(
    private val authRepo: AuthRepo
) : ViewModel() {

    private var _verificationId = ""

    private val _otpSent = MutableStateFlow(false)
    val otpSent = _otpSent.asStateFlow()

    private val _loginSuccess = MutableStateFlow(false)
    val loginSuccess = _loginSuccess.asStateFlow()

    private val _error = MutableStateFlow("")
    val error = _error.asStateFlow()


    fun sendOtp(
        activity: Activity,
        phoneNumber: String
    ) {

        authRepo.sendOtp(

            activity = activity,

            phoneNumber = phoneNumber,

            onCodeSent = { verificationId ->

                _verificationId = verificationId
                _otpSent.value = true

            },

            onVerificationCompleted = {

                _loginSuccess.value = true

            },

            onVerificationFailed = {

                _error.value = it

            }

        )

    }


    fun verifyOtp(
        otp: String
    ) {

        authRepo.verifyOtp(

            verificationId = _verificationId,

            otp = otp,

            onSuccess = {

                _loginSuccess.value = true

            },

            onFailure = {

                _error.value = it

            }

        )

    }

}
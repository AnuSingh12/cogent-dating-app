package com.example.anucogent.data.repository

import android.app.Activity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class AuthRepo {

    private val auth = FirebaseAuth.getInstance()

    fun sendOtp(
        activity: Activity,
        phoneNumber: String,
        onCodeSent: (String) -> Unit,
        onVerificationCompleted: (PhoneAuthCredential) -> Unit,
        onVerificationFailed: (String) -> Unit
    ) {

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                override fun onVerificationCompleted(
                    credential: PhoneAuthCredential
                ) {
                    onVerificationCompleted(credential)
                }

                override fun onVerificationFailed(
                    e: FirebaseException
                ) {
                    onVerificationFailed(e.message ?: "OTP Failed")
                }

                override fun onCodeSent(
                    verificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    super.onCodeSent(verificationId, token)
                    onCodeSent(verificationId)
                }

            })
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }


    fun verifyOtp(
        verificationId: String,
        otp: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {

        val credential =
            PhoneAuthProvider.getCredential(
                verificationId,
                otp
            )

        auth.signInWithCredential(credential)
            .addOnCompleteListener {

                if (it.isSuccessful) {
                    onSuccess()
                } else {
                    onFailure(
                        it.exception?.message ?: "Verification Failed"
                    )
                }

            }

    }

}
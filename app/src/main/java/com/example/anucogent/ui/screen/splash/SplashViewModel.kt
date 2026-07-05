package com.example.anucogent.ui.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class SplashViewModel : ViewModel() {

    fun startSplash(
        onNavigate: () -> Unit
    ) {

        viewModelScope.launch {

            delay(2000.milliseconds)

            onNavigate()

        }

    }

}
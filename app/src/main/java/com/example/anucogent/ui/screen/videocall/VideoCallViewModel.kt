package com.example.anucogent.ui.screen.videocall

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class VideoCallViewModel : ViewModel() {

    companion object {

        const val APP_ID =
            "a9fb65314d76436186c7be5ed6a0f26b"

        const val TOKEN =
            "007eJxTYPgxK6tGIH8/R8Jnjy0z3ZsYdOMniPKfPKOq/bXpTpUuE4cCQ6JlWpKZqbGhSYq5mYmxmaGFWbJ5UqppaopZokGakVnSAkuvrIZARoYZe8RYGBkgEMRnZ0jMK01OzMlhYAAAA80eKw=="

        const val CHANNEL_NAME =
            "anucall"

    }

    private val _joined = MutableStateFlow(false)
    val joined = _joined.asStateFlow()

    private val _remoteUid = MutableStateFlow(0)
    val remoteUid = _remoteUid.asStateFlow()

    fun joinedCall() {

        _joined.value = true

    }

    fun leftCall() {

        _joined.value = false

        _remoteUid.value = 0

    }

    fun userJoined(uid: Int) {

        _remoteUid.value = uid

    }

}
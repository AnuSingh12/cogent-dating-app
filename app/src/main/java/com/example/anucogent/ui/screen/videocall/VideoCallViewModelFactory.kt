package com.example.anucogent.ui.screen.videocall

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class VideoCallViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (
            modelClass.isAssignableFrom(
                VideoCallViewModel::class.java
            )
        ) {

            @Suppress("UNCHECKED_CAST")

            return VideoCallViewModel() as T

        }

        throw IllegalArgumentException(
            "Unknown ViewModel"
        )

    }

}
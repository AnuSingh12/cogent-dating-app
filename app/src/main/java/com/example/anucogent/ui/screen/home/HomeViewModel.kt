package com.example.anucogent.ui.screen.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.anucogent.R
import com.example.anucogent.data.model.user.User

class HomeViewModel : ViewModel() {

    private val _currentIndex = MutableStateFlow(0)
    val currentIndex = _currentIndex.asStateFlow()

    private val _userList = MutableStateFlow(

        listOf(

            User(
                1,
                "Ananya",
                22,
                "Software Engineer",
                "Jaipur",
                R.drawable.img_1
            ),

            User(
                2,
                "Priya",
                21,
                "Doctor",
                "Kanpur",
                R.drawable.img_2
            ),

            User(
                3,
                "Riya",
                23,
                "Teacher",
                "Lucknow",
                R.drawable.img_3
            ),

            User(
                4,
                "Neha",
                20,
                "Designer",
                "Dur daraj se",
                R.drawable.img_4
            )

        )

    )

    val userList = _userList.asStateFlow()


    fun removeUser() {

        if (_currentIndex.value < _userList.value.lastIndex) {

            _currentIndex.value++

        } else {

            _currentIndex.value = _userList.value.size

        }

    }

    fun likeUser() {

        // Future me backend aayega to yaha save hoga

    }


}
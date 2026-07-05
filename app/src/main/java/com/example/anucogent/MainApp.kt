package com.example.anucogent

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.example.anucogent.navigation.HomeKey
import com.example.anucogent.navigation.LoginKey
import com.example.anucogent.navigation.SplashKey
import com.example.anucogent.navigation.VideoCallKey
import com.example.anucogent.ui.screen.home.HomeScreen
import com.example.anucogent.ui.screen.login.LoginScreen
import com.example.anucogent.ui.screen.splash.SplashScreen
import com.example.anucogent.ui.screen.videocall.VideoCallScreen

@Composable
fun MainApp() {

    val backStack = remember { mutableStateListOf<Any>(SplashKey) }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->

            when (key) {

                is SplashKey -> NavEntry(key) {

                    SplashScreen(
                        navigateToLogin = {
                            backStack.clear()
                            backStack.add(LoginKey)
                        }
                    )

                }

                is LoginKey -> NavEntry(key) {

                    LoginScreen(
                        navigateToHome = {
                            backStack.clear()
                            backStack.add(HomeKey)
                        }
                    )

                }

                is HomeKey -> NavEntry(key) {

                    HomeScreen(

                        navigateToVideoCall = {

                            backStack.add(VideoCallKey)

                        }

                    )

                }

                is VideoCallKey -> NavEntry(key) {

                    VideoCallScreen()

                }

                else -> NavEntry(Unit) {

                    Text("Unknown")

                }
            }
        }
    )
}

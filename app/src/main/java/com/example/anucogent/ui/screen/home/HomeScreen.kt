package com.example.anucogent.ui.screen.home


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.anucogent.ui.component.ProfileCard

@Composable
fun HomeScreen(

    navigateToVideoCall: () -> Unit

) {

    val viewModel: HomeViewModel = viewModel(

        factory = HomeViewModelFactory()

    )

    val userList by viewModel.userList.collectAsState()
    val currentIndex by viewModel.currentIndex.collectAsState()
    val currentUser =
        if (currentIndex < userList.size)
            userList[currentIndex]
        else
            null

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "❤️ Anu Dating",
            style = MaterialTheme.typography.headlineLarge
        )

        Text(
            text = "Discover People Around You",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(20.dp))


        if (currentUser != null) {

            ProfileCard(

                user = currentUser,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)

            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(

                horizontalArrangement = Arrangement.SpaceEvenly,

                modifier = Modifier.fillMaxWidth()

            ) {

                FloatingActionButton(

                    onClick = {

                        viewModel.removeUser()

                    }

                ) {

                    Icon(

                        imageVector = Icons.Default.Close,

                        contentDescription = null

                    )

                }

                FloatingActionButton(

                    onClick = {

                        viewModel.likeUser()
                        navigateToVideoCall()

                    }

                ) {

                    Icon(

                        imageVector = Icons.Default.Favorite,

                        contentDescription = null

                    )

                }

            }

        } else {

            Box(

                modifier = Modifier.fillMaxSize(),

                contentAlignment = Alignment.Center

            ) {

                Text("No More Profiles")

            }

        }

    }

}
package com.example.anucogent.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.anucogent.data.model.user.User

@Composable
fun ProfileCard(

    user: User,

    modifier: Modifier = Modifier

) {

    Card(

        modifier = modifier,

        shape = RoundedCornerShape(20.dp),

        elevation = CardDefaults.cardElevation(8.dp)

    ) {

        Box {

            Image(

                painter = painterResource(id = user.image),

                contentDescription = null,

                modifier = Modifier.fillMaxSize(),

                contentScale = ContentScale.Crop

            )

            Box(

                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .align(Alignment.BottomCenter)
                    .background(

                        Brush.verticalGradient(

                            listOf(
                                Color.Transparent,
                                Color.Black
                            )

                        )

                    )

            )

            Column(

                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(20.dp)

            ) {

                Text(

                    text = "${user.name}, ${user.age}",

                    style = MaterialTheme.typography.headlineSmall,

                    color = Color.White,

                    fontWeight = FontWeight.Bold

                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(

                    text = user.profession,

                    style = MaterialTheme.typography.bodyLarge,

                    color = Color.White

                )
                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = user.location,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )

            }

        }

    }

}
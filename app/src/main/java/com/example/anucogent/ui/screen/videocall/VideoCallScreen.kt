package com.example.anucogent.ui.screen.videocall

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import io.agora.rtc2.IRtcEngineEventHandler
import io.agora.rtc2.RtcEngine
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CallEnd
import androidx.compose.material.icons.filled.Cameraswitch
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun VideoCallScreen() {

    val context = LocalContext.current

    val viewModel: VideoCallViewModel = viewModel(
        factory = VideoCallViewModelFactory()
    )

    val isJoined by viewModel.joined.collectAsState()

    var permissionGranted by remember {
        mutableStateOf(false)
    }

    var rtcEngine by remember {
        mutableStateOf<RtcEngine?>(null)
    }

    val permissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->

            permissionGranted =
                permissions.values.all { it }

        }

    LaunchedEffect(Unit) {

        val cameraPermission =

            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED

        val audioPermission =

            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.RECORD_AUDIO
            ) == PackageManager.PERMISSION_GRANTED

        if (cameraPermission && audioPermission) {

            permissionGranted = true

        } else {

            permissionLauncher.launch(

                arrayOf(

                    Manifest.permission.CAMERA,

                    Manifest.permission.RECORD_AUDIO

                )

            )

        }

    }

    val rtcHandler = remember {

        object : IRtcEngineEventHandler() {

            override fun onJoinChannelSuccess(

                channel: String?,

                uid: Int,

                elapsed: Int

            ) {

                viewModel.joinedCall()

            }

            override fun onUserJoined(

                uid: Int,

                elapsed: Int

            ) {

                viewModel.userJoined(uid)

            }

            override fun onUserOffline(

                uid: Int,

                reason: Int

            ) {

                viewModel.leftCall()

            }

        }

    }

    LaunchedEffect(permissionGranted) {

        if (permissionGranted) {

            rtcEngine =

                RtcEngine.create(

                    context,

                    VideoCallViewModel.APP_ID,

                    rtcHandler

                )

            rtcEngine?.enableVideo()

        }

    }

    DisposableEffect(Unit) {

        onDispose {

            rtcEngine?.leaveChannel()

            RtcEngine.destroy()

        }

    }

    // ======= UI PART-2 ME AAYEGA =======

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Video Call",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(
                    Color.Black,
                    RoundedCornerShape(20.dp)
                ),
            contentAlignment = Alignment.Center
        ) {

            if (isJoined) {

                Text(
                    text = "Connecting...",
                    color = Color.White
                )

            } else {

                Text(
                    text = "Tap Join Call",
                    color = Color.White
                )

            }

        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Row(

            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceEvenly

        ) {

            FloatingActionButton(

                onClick = {

                    rtcEngine?.joinChannel(

                        VideoCallViewModel.TOKEN,

                        VideoCallViewModel.CHANNEL_NAME,

                        0,

                        io.agora.rtc2.ChannelMediaOptions()

                    )

                }

            ) {

                Icon(

                    imageVector = Icons.Default.Call,

                    contentDescription = null

                )

            }

            FloatingActionButton(

                onClick = {

                    rtcEngine?.leaveChannel()

                    viewModel.leftCall()

                }

            ) {

                Icon(

                    imageVector = Icons.Default.CallEnd,

                    contentDescription = null

                )

            }

            FloatingActionButton(

                onClick = {

                    rtcEngine?.muteLocalAudioStream(true)

                }

            ) {

                Icon(

                    imageVector = Icons.Default.Mic,

                    contentDescription = null

                )

            }

            FloatingActionButton(

                onClick = {

                    rtcEngine?.switchCamera()

                }

            ) {

                Icon(

                    imageVector = Icons.Default.Cameraswitch,

                    contentDescription = null

                )

            }

        }

    }




}
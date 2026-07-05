package com.example.anucogent.data.repository

import android.content.Context
import io.agora.rtc2.ChannelMediaOptions
import io.agora.rtc2.Constants
import io.agora.rtc2.IRtcEngineEventHandler
import io.agora.rtc2.RtcEngine

class AgoraRepo {

    private var rtcEngine: RtcEngine? = null

    fun initializeEngine(
        context: Context,
        appId: String,
        eventHandler: IRtcEngineEventHandler
    ) {

        rtcEngine = RtcEngine.create(
            context,
            appId,
            eventHandler
        )

    }

    fun joinChannel(

        token: String,

        channelName: String,

        uid: Int

    ) {

        rtcEngine?.joinChannel(

            token,

            channelName,

            uid,

            ChannelMediaOptions().apply {

                channelProfile =
                    Constants.CHANNEL_PROFILE_COMMUNICATION

                clientRoleType =
                    Constants.CLIENT_ROLE_BROADCASTER

            }

        )

    }

    fun leaveChannel() {

        rtcEngine?.leaveChannel()

    }

    fun switchCamera() {

        rtcEngine?.switchCamera()

    }

    fun muteMic(
        mute: Boolean
    ) {

        rtcEngine?.muteLocalAudioStream(mute)

    }

    fun destroy() {

        rtcEngine?.leaveChannel()

        RtcEngine.destroy()

        rtcEngine = null

    }

}
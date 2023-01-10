package com.baharudin.notifications.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.baharudin.notifications.common.wrapper.NotificationWrapper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AlarmNotificationReceiver : BroadcastReceiver() {

    @Inject
    lateinit var notificationWrapper: NotificationWrapper

    override fun onReceive(context: Context?, intent: Intent?) {
        notificationWrapper.notify(
            contentTitle = intent?.getStringExtra(NOTIFICATION_TITLE) ?: "Title",
            contentText = intent?.getStringExtra(NOTIFICATION_TEXT) ?: "Content Text"
        )
    }

    companion object{
        const val NOTIFICATION_TITLE = "NOTIFICATION_TITLE"
        const val NOTIFICATION_TEXT = "NOTIFICATION_TEXT"
    }

}
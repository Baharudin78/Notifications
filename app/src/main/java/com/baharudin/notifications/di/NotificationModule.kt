package com.baharudin.notifications.di

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.VISIBILITY_PRIVATE
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import com.baharudin.notifications.MainActivity
import com.baharudin.notifications.R
import com.baharudin.notifications.common.anotatation.AlarmPendingIntent
import com.baharudin.notifications.navigation.MY_ARGS
import com.baharudin.notifications.navigation.MY_URI
import com.baharudin.notifications.receiver.AlarmNotificationReceiver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotificationModule {

    @Singleton
    @Provides
    fun provideNotificationBuilder(
        @ApplicationContext context: Context
    ) : NotificationCompat.Builder{

        val clickIntent = Intent(
            Intent.ACTION_VIEW,
            "$MY_URI/$MY_ARGS= Coming From Nofication".toUri(),
            context,
            MainActivity::class.java
        )

        val clickPendingIntent : PendingIntent = TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(clickIntent)
            getPendingIntent(1, PendingIntent.FLAG_IMMUTABLE)
        }
        return NotificationCompat.Builder(context, "Main Channel ID")
            .setContentTitle("Welcome")
            .setContentText("Notfication App")
            .setSmallIcon(R.drawable.ic_notification)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setVisibility(VISIBILITY_PRIVATE)
            .setPublicVersion(
                NotificationCompat.Builder(context, "Main Channel ID")
                    .setContentTitle("Hidden")
                    .setContentText("Unlock to see the message")
                    .build()
            )
            .setContentIntent(clickPendingIntent)
    }

    @Singleton
    @Provides
    fun provideNotificationManager(
        @ApplicationContext context : Context
    ) : NotificationManagerCompat {
        val notificationManager = NotificationManagerCompat.from(context)
        val channel = NotificationChannel(
            "Main Channel ID",
            "Main Channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)
        return notificationManager
    }

    @Singleton
    @Provides
    fun provideAlarmManager(
        @ApplicationContext context: Context
    ) : AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    @Singleton
    @AlarmPendingIntent
    @Provides
    fun provideAlarmPendingIntent(
        @ApplicationContext context: Context
    ) : PendingIntent =
        PendingIntent.getBroadcast(
            context,
            2,
            Intent(context, AlarmNotificationReceiver::class.java).apply {
                putExtra(AlarmNotificationReceiver.NOTIFICATION_TITLE, "How are you today")
                putExtra(AlarmNotificationReceiver.NOTIFICATION_TEXT, "Learning notification")
            },
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
}
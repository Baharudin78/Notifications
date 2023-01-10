package com.baharudin.notifications.common.wrapper

import android.app.AlarmManager
import android.app.PendingIntent
import com.baharudin.notifications.common.anotatation.AlarmPendingIntent
import javax.inject.Inject

class AlarmWrapper @Inject constructor(
    private val alarmManager: AlarmManager,
    @AlarmPendingIntent private val alarmPendingIntent: PendingIntent
) {
    fun sceduleAlarm(time : Long) {
        println("SCHEDULE ALARM")
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            alarmPendingIntent
        )
    }
}
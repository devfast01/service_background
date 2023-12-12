package com.example.background_service.Example_2

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.pm.ServiceInfo
import android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_LOCATION
import android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import com.example.background_service.Example_2.Constants.CHANNEL_ID
import com.example.background_service.Example_2.Constants.MUSIC_NOTIFICATION_ID
import com.example.background_service.MainActivity
import com.example.background_service.R

class MyService_2 : Service() {
    private lateinit var musicPlayer: MediaPlayer

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        initMusic()
        createNotificationChanel()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        showNotification()

        return START_STICKY
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnspecifiedImmutableFlag")
    private fun showNotification() {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent,  PendingIntent.FLAG_IMMUTABLE)

        val notification = Notification
            .Builder(this, CHANNEL_ID)
            .setContentText("Music Player")
            .setSmallIcon(R.drawable.ic_alaram)
            .setContentIntent(pendingIntent)
            .build()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            startForeground(MUSIC_NOTIFICATION_ID, notification, ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PROJECTION)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ObsoleteSdkInt")
    private fun createNotificationChanel() {
        if (Build.VERSION.SDK_INT>=23) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID, "My Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            val manager = getSystemService(NotificationManager::class.java)

            manager.createNotificationChannel(serviceChannel)
        }
    }

    private fun initMusic() {
        musicPlayer = MediaPlayer.create(this, R.raw.pirates)
        musicPlayer.isLooping = true
        musicPlayer.setVolume(100F, 100F)
    }
}
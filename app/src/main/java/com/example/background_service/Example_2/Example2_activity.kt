package com.example.background_service.Example_2

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.background_service.MainActivity
import com.example.background_service.R
import com.example.background_service.databinding.ActivityExample2Binding

@Suppress("DEPRECATION")
class Example2_activity : AppCompatActivity() {
    private lateinit var binding: ActivityExample2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExample2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            startStopService()
        }
    }

    private fun startStopService() {
        if (isMyServiceRunning((MyService_2::class.java))) {
            Toast.makeText(
                this,
                "Service Stopped",
                Toast.LENGTH_SHORT
            ).show()

            stopService(Intent(this,MyService_2::class.java))

        } else {
            Toast.makeText(
                this,
                "Service Started",
                Toast.LENGTH_SHORT
            ).show()
            startService(Intent(this,MyService_2::class.java))
        }
    }

    private fun isMyServiceRunning(mclass: Class<MyService_2>): Boolean {
        val manager: ActivityManager = getSystemService(
            Context.ACTIVITY_SERVICE
        ) as ActivityManager

        for (service: ActivityManager.RunningServiceInfo in
        manager.getRunningServices(Integer.MAX_VALUE)) {
            if (mclass.name.equals(service.service.className)) {
                return true
            }
        }
        return false
    }
}
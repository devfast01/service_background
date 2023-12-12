package com.example.background_service.Example_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.background_service.R
import com.example.background_service.databinding.ActivityExample3Binding

class Example3_activity : AppCompatActivity() {
    private lateinit var binding: ActivityExample3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExample3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonStart.setOnClickListener(View.OnClickListener {
            ForegroundService.startService(this, "Foreground Service is running...")
        })
        binding.buttonStop.setOnClickListener(View.OnClickListener {
            ForegroundService.stopService(this)
        })
    }
}
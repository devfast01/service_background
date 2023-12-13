package com.example.background_service.Example_4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.background_service.R
import com.example.background_service.databinding.ActivityExample4Binding

class Example4_activity : AppCompatActivity() {
    private lateinit var binding: ActivityExample4Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExample4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonStart.setOnClickListener {
            startService(Intent(this,newService::class.java))
        }

        binding.buttonStop.setOnClickListener {
            stopService(Intent(this,newService::class.java))
        }
    }
}
@file:Suppress("DEPRECATION")

package com.example.background_service

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var button: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.btn_start)

        button?.setOnClickListener {
            val intent=Intent(this,MyService::class.java)
            intent.putExtra("name","Geek for Geeks")
            startService(intent)
        }
    }
}
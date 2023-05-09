package com.example.tasktylium.view

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.tasktylium.databinding.ActivitySplashscreenBinding


import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashScreenActivity :AppCompatActivity() {
    private lateinit var binding : ActivitySplashscreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleScope.launch {
            delay(1000)
            val intent= Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
        }
    }

}
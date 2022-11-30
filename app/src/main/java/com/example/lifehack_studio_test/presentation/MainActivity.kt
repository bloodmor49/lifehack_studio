package com.example.lifehack_studio_test.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lifehack_studio_test.R
import com.example.lifehack_studio_test.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
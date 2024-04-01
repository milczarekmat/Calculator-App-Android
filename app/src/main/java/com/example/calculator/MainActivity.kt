package com.example.calculator

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        setUpListeners()
    }

    private fun setUpListeners() {
        binding.simpleCalculatorBtn.setOnClickListener {
            val intent = Intent(this, SimpleCalculatorActivity::class.java)
            startActivity(intent)
        }

        binding.advancedBtn.setOnClickListener {
            val intent = Intent(this, AdvancedCalculatorActivity::class.java)
            startActivity(intent)
        }

        binding.aboutBtn.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }

        binding.exitBtn.setOnClickListener {
            finishAffinity()
            exitProcess(0)
        }
    }
}
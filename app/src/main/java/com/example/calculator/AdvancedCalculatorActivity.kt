package com.example.calculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityAdvancedCalculatorBinding

class AdvancedCalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdvancedCalculatorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdvancedCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        setUpListeners()
    }
    private fun setUpListeners() {
        binding.oneBtn.setOnClickListener {
//            super.handleDigitClick("1", binding)
        }
    }
}
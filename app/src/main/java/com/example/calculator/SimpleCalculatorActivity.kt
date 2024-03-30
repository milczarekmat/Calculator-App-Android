package com.example.calculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.binding.ViewManagerStrategy
import com.example.calculator.binding.simple.SimpleCalculatorViewManager
import com.example.calculator.databinding.ActivitySimpleCalculatorBinding
import com.example.calculator.listener.CalculatorClickListener

class SimpleCalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySimpleCalculatorBinding
    private lateinit var calculatorViewManager: ViewManagerStrategy
    private lateinit var calculatorClickListener: CalculatorClickListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySimpleCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        calculatorViewManager = SimpleCalculatorViewManager(binding)

        calculatorClickListener = CalculatorClickListener(calculatorViewManager)
        calculatorClickListener.setUpListeners()
    }
}
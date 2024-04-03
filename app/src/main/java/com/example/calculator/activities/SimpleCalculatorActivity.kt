package com.example.calculator.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.viewManagers.AbstractViewManager
import com.example.calculator.viewManagers.simple.SimpleCalculatorViewManager
import com.example.calculator.databinding.ActivitySimpleCalculatorBinding
import com.example.calculator.listeners.simple.SimpleCalculatorClickListener

class SimpleCalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySimpleCalculatorBinding
    private lateinit var calculatorViewManager: AbstractViewManager
    private lateinit var simpleCalculatorClickListener: SimpleCalculatorClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySimpleCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        calculatorViewManager = SimpleCalculatorViewManager(binding)

        simpleCalculatorClickListener = SimpleCalculatorClickListener(calculatorViewManager)
        simpleCalculatorClickListener.setUpSimpleCalculatorListeners()
    }

    override fun onResume() {
        super.onResume()
        if (binding.calculatorResultTV.text.isNotEmpty()) {
            calculatorViewManager.setIsInitState(false)
            calculatorViewManager.setIsNewOperation(true)
            simpleCalculatorClickListener.setFirstOperand()
        }

        if (binding.calculatorOperatorTV.text.isNotEmpty() && binding.calculatorResultTV.text.isEmpty()) {
                calculatorViewManager.setIsEntryClearPressed(true)
            }
    }
}
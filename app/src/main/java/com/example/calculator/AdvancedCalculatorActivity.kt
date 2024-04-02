package com.example.calculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.bindingControllers.ViewManagerStrategy
import com.example.calculator.bindingControllers.advanced.AdvancedCalculatorViewManager
import com.example.calculator.databinding.ActivityAdvancedCalculatorBinding
import com.example.calculator.listener.advanced.AdvancedCalculatorClickListener

class AdvancedCalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdvancedCalculatorBinding
    private lateinit var calculatorViewManager: ViewManagerStrategy
    private lateinit var calculatorClickListener: AdvancedCalculatorClickListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdvancedCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        calculatorViewManager = AdvancedCalculatorViewManager(binding)

        calculatorClickListener = AdvancedCalculatorClickListener(calculatorViewManager)
        calculatorClickListener.setUpSimpleCalculatorListeners()
        calculatorClickListener.setUpAdvancedCalculatorListeners()
    }

    override fun onResume() {
        super.onResume()
        if (binding.calculatorResultTV.text.isNotEmpty()) {
            calculatorViewManager.setIsInitState(false)
            calculatorViewManager.setIsNewOperation(true)
            calculatorClickListener.setFirstOperand()
        }

        if (binding.calculatorOperatorTV.text.isNotEmpty() && binding.calculatorResultTV.text.isEmpty()) {
            calculatorViewManager.setIsEntryClearPressed(true)
        }
    }
}
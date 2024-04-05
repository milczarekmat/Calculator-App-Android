package com.example.calculator.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.viewManagers.AbstractViewManager
import com.example.calculator.viewManagers.advanced.AdvancedCalculatorViewManager
import com.example.calculator.databinding.ActivityAdvancedCalculatorBinding
import com.example.calculator.listeners.advanced.AdvancedCalculatorClickListener

class AdvancedCalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdvancedCalculatorBinding
    private lateinit var calculatorViewManager: AbstractViewManager
    private lateinit var advancedCalculatorClickListener: AdvancedCalculatorClickListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdvancedCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        calculatorViewManager = AdvancedCalculatorViewManager(binding)

        advancedCalculatorClickListener = AdvancedCalculatorClickListener(calculatorViewManager, this)
        advancedCalculatorClickListener.setUpSimpleCalculatorListeners()
        advancedCalculatorClickListener.setUpAdvancedCalculatorListeners()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        calculatorViewManager.setMainTextView(savedInstanceState.getString("result") ?: "")
        calculatorViewManager.setOperator(savedInstanceState.getString("operator") ?: "")
        calculatorViewManager.setIsInitState(savedInstanceState.getBoolean("isInitState"))
        calculatorViewManager.setIsNewOperation(savedInstanceState.getBoolean("isNewOperation"))
        calculatorViewManager.setIsEntryClearPressed(savedInstanceState.getBoolean("isEntryClearPressed"))
        advancedCalculatorClickListener.setFirstOperand(savedInstanceState.getDouble("firstOperand"))

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("result", binding.calculatorResultTV.text.toString())
        outState.putString("operator", binding.calculatorOperatorTV.text.toString())
        outState.putBoolean("isInitState", calculatorViewManager.isInitState())
        outState.putBoolean("isNewOperation", calculatorViewManager.isNewOperation())
        outState.putBoolean("isEntryClearPressed", calculatorViewManager.isEntryClearPressed())
        outState.putDouble("firstOperand", advancedCalculatorClickListener.getFirstOperand())
    }
}
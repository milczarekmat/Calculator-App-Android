package com.example.calculator.activities

import android.os.Bundle
import android.os.PersistableBundle
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

        simpleCalculatorClickListener = SimpleCalculatorClickListener(calculatorViewManager, this)
        simpleCalculatorClickListener.setUpSimpleCalculatorListeners()
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        calculatorViewManager.setMainTextView(savedInstanceState.getString("result") ?: "")
        calculatorViewManager.setOperator(savedInstanceState.getString("operator") ?: "")
        calculatorViewManager.setIsInitState(savedInstanceState.getBoolean("isInitState"))
        calculatorViewManager.setIsNewOperation(savedInstanceState.getBoolean("isNewOperation"))
        calculatorViewManager.setIsEntryClearPressed(savedInstanceState.getBoolean("isEntryClearPressed"))
        simpleCalculatorClickListener.setFirstOperand(savedInstanceState.getDouble("firstOperand"))

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("result", binding.calculatorResultTV.text.toString())
        outState.putString("operator", binding.calculatorOperatorTV.text.toString())
        outState.putBoolean("isInitState", calculatorViewManager.isInitState())
        outState.putBoolean("isNewOperation", calculatorViewManager.isNewOperation())
        outState.putBoolean("isEntryClearPressed", calculatorViewManager.isEntryClearPressed())
        outState.putDouble("firstOperand", simpleCalculatorClickListener.getFirstOperand())
    }
}
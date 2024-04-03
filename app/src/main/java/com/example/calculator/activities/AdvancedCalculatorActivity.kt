package com.example.calculator.activities

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.viewManagers.AbstractViewManager
import com.example.calculator.viewManagers.advanced.AdvancedCalculatorViewManager
import com.example.calculator.databinding.ActivityAdvancedCalculatorBinding
import com.example.calculator.listeners.advanced.AdvancedCalculatorClickListener

class AdvancedCalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdvancedCalculatorBinding
    private lateinit var calculatorViewManager: AbstractViewManager
    private lateinit var calculatorClickListener: AdvancedCalculatorClickListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdvancedCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        calculatorViewManager = AdvancedCalculatorViewManager(binding)

        calculatorClickListener = AdvancedCalculatorClickListener(calculatorViewManager, this)
        calculatorClickListener.setUpSimpleCalculatorListeners()
        calculatorClickListener.setUpAdvancedCalculatorListeners()
    }

//    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
//        super.onSaveInstanceState(outState, outPersistentState)
//        outState.putString("")
//    }
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
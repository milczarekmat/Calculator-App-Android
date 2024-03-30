package com.example.calculator.bindingControllers.simple

import com.example.calculator.bindingControllers.ViewManagerStrategy
import com.example.calculator.databinding.ActivitySimpleCalculatorBinding

class SimpleCalculatorViewManager(
    private val binding: ActivitySimpleCalculatorBinding,
) : ViewManagerStrategy() {
    override fun setMainTextView(value: String) {
        binding.calculatorResultTV.text = value
    }

    override fun appendMainTextView(value: String) {
        binding.calculatorResultTV.append(value)
    }

    override fun setOperator(value: String) {
        binding.calculatorOperatorTV.text = value
    }

    override fun isMainTextViewEmpty(): Boolean {
        return binding.calculatorResultTV.text.isEmpty()
    }

    override fun getCurrentMainText(): String {
        return binding.calculatorResultTV.text.toString()
    }

    override fun getCurrentOperator(): String {
        return binding.calculatorOperatorTV.text.toString()
    }

    override fun clearMainTextView() {
        binding.calculatorResultTV.text = ""
    }

    override fun clearOperator() {
        binding.calculatorOperatorTV.text = ""
    }

    override fun isOperatorInserted(): Boolean {
        return binding.calculatorOperatorTV.text.isNotEmpty()
    }

    override fun getBinding(): Any {
        return binding
    }
}
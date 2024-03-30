package com.example.calculator.listener

import com.example.calculator.binding.ViewManagerStrategy

class CalculatorClickListener(private val viewManager: ViewManagerStrategy) {

    fun onDigitClick(digit: String) {
        viewManager.setMainTextView(digit)
    }

}
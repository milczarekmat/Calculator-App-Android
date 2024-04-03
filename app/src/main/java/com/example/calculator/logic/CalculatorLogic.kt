package com.example.calculator.logic

import android.widget.Toast
import android.content.Context
import kotlin.math.pow

class CalculatorLogic(private val context: Context) {
    fun evaluateExpression(
        firstOperand: Double,
        secondOperand: Double,
        operator: String
    ): Double {
        if (operator.contains("^")) {
            return firstOperand.pow(secondOperand)
        }

        return when (operator) {
            "+" -> firstOperand + secondOperand
            "-" -> firstOperand - secondOperand
            "*" -> firstOperand * secondOperand
            "/" -> {
                if (secondOperand == 0.0) {
                    showToast("Nie mozna dzielic przez zero")
                }
                return firstOperand / secondOperand
            }

            else -> firstOperand
        }
    }

    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
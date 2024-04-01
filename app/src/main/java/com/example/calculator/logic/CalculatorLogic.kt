package com.example.calculator.logic

import kotlin.math.pow

class CalculatorLogic {
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
            "/" -> firstOperand / secondOperand
            else -> firstOperand
        }
    }
}
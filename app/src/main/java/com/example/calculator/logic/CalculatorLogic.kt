package com.example.calculator.logic

class CalculatorLogic {
    fun evaluateExpression(
        firstOperand: Double,
        secondOperand: Double,
        operator: String
    ): Double {
        return when (operator) {
            "+" -> firstOperand + secondOperand
            "-" -> firstOperand - secondOperand
            "*" -> firstOperand * secondOperand
            "/" -> firstOperand / secondOperand
            else -> firstOperand
        }
    }
}
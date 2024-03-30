package com.example.calculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivitySimpleCalculatorBinding

class SimpleCalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySimpleCalculatorBinding

    private var isOperatorInserted = false
    private var isInitState = true
    private var newOperationFlag = false
    private var CEClearAll = false
    private var firstOperand: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySimpleCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        setUpListeners()
    }

    private fun handleDigitClick(digit: String) {
        if (newOperationFlag) {
            binding.calculatorResultTV.text = ""
            newOperationFlag = false
        }
        binding.calculatorResultTV.append(digit)
        CEClearAll = false
    }

    private fun handleOperatorClick(operator: String) {
        if (isInitState && binding.calculatorResultTV.text.isEmpty()) {
            return
        }

        if (isOperatorInserted) {
            if (newOperationFlag) { // replace operator
                val currentOperator = binding.calculatorOperatorTV.text.toString()
                binding.calculatorOperatorTV.text =
                    currentOperator.replace(currentOperator.last(), operator[0])
            } else { // evaluate expression and insert new operator
                handleEqualsClick()
                insertOperator(operator)
                firstOperand = binding.calculatorResultTV.text.toString().toDouble()
            }
        } else {
            insertOperator(operator)
            clearAndSaveOperand()
        }
    }

    private fun insertOperator(operator: String) {
        binding.calculatorOperatorTV.append(operator)

        isOperatorInserted = true
        isInitState = false
        newOperationFlag = true
    }

    private fun clearAndSaveOperand() {
        if (binding.calculatorResultTV.text.isEmpty()) {
            return
        }

        firstOperand = binding.calculatorResultTV.text.toString().toDouble()
    }

    private fun handleDecimalClick() {
        val currentText = binding.calculatorResultTV.text.toString()

        if (currentText.contains(".")) {
            return
        }

        CEClearAll = false

        if (currentText.isEmpty() || isOperatorInserted) {
            binding.calculatorResultTV.append("0.")
        } else {
            binding.calculatorResultTV.append(".")
        }
    }

    private fun handleBackSpaceClick() {
        val resultTV = binding.calculatorResultTV
        if (resultTV.text.isNotEmpty()) {
            binding.calculatorResultTV.text = resultTV.text.substring(0, resultTV.text.length - 1)
            if (resultTV.text.isNotEmpty() && resultTV.text.toString().last() == '.') {
                resultTV.text = resultTV.text.substring(0, resultTV.text.length - 1)
            }
        }

        if (resultTV.text.isEmpty()) {
            isInitState = true
        }
    }

    private fun handleSignClick() {
        val currentText = binding.calculatorResultTV.text.toString()
        if (currentText.isNotEmpty()) {
            val firstChar = currentText[0]
            if (firstChar == '-') {
                binding.calculatorResultTV.text = currentText.substring(1)
            } else {
                binding.calculatorResultTV.text = "-$currentText"
            }
        }
    }

    private fun evaluateExpression(
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

    private fun handleEqualsClick() {
        if (binding.calculatorOperatorTV.text.isEmpty()) {
            return
        }

        val secondOperand = binding.calculatorResultTV.text.toString().toDouble()

        val result = evaluateExpression(
            firstOperand,
            secondOperand,
            binding.calculatorOperatorTV.text.toString()
        )

        if (result % 1.0 == 0.0) {
            binding.calculatorResultTV.text = result.toInt().toString()
        } else {
            binding.calculatorResultTV.text = result.toString()
        }

        firstOperand = result

        isOperatorInserted = false
        newOperationFlag = true
        binding.calculatorOperatorTV.text = ""
    }

    private fun clearAll() {
        binding.calculatorResultTV.text = ""
        binding.calculatorOperatorTV.text = ""
        firstOperand = 0.0
        isOperatorInserted = false
        isInitState = true
        newOperationFlag = false
        CEClearAll = false
    }

    private fun handleCEClick() {
        if (CEClearAll) {
            clearAll()
            return
        }

        if (newOperationFlag) {
            return
        }

        if (binding.calculatorResultTV.text.isNotEmpty()) {
            binding.calculatorResultTV.text = ""
        }
        CEClearAll = true

    }

    private fun setUpListeners() {
        binding.oneBtn.setOnClickListener {
            handleDigitClick("1")
        }

        binding.twoBtn.setOnClickListener {
            handleDigitClick("2")
        }

        binding.threeBtn.setOnClickListener {
            handleDigitClick("3")
        }

        binding.fourBtn.setOnClickListener {
            handleDigitClick("4")
        }

        binding.fiveBtn.setOnClickListener {
            handleDigitClick("5")
        }

        binding.sixBtn.setOnClickListener {
            handleDigitClick("6")
        }

        binding.sevenBtn.setOnClickListener {
            handleDigitClick("7")
        }

        binding.eightBtn.setOnClickListener {
            handleDigitClick("8")
        }

        binding.nineBtn.setOnClickListener {
            handleDigitClick("9")
        }

        binding.zeroBtn.setOnClickListener {
            handleDigitClick("0")
        }

        binding.addBtn.setOnClickListener {
            handleOperatorClick("+")
        }

        binding.subBtn.setOnClickListener {
            handleOperatorClick("-")
        }

        binding.multiplyBtn.setOnClickListener {
            handleOperatorClick("*")
        }

        binding.divideBtn.setOnClickListener {
            handleOperatorClick("/")
        }

        binding.equalsBtn.setOnClickListener {
            handleEqualsClick()
        }

        binding.backspaceBtn.setOnClickListener {
            handleBackSpaceClick()
        }

        binding.ACbtn.setOnClickListener {
            clearAll()
        }

        binding.CEbtn.setOnClickListener {
            handleCEClick()
        }

        binding.decimalBtn.setOnClickListener {
            handleDecimalClick()
        }

        binding.signBtn.setOnClickListener {
            handleSignClick()
        }
    }
}
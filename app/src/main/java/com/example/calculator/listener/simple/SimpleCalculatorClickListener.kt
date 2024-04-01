package com.example.calculator.listener.simple

import com.example.calculator.bindingControllers.ViewManagerStrategy
import com.example.calculator.databinding.ActivitySimpleCalculatorBinding
import com.example.calculator.logic.CalculatorLogic

open class SimpleCalculatorClickListener(private val calculatorViewManager: ViewManagerStrategy) {
    private lateinit var binding: ActivitySimpleCalculatorBinding
    private var firstOperand: Double = 0.0
    private val calculatorLogic = CalculatorLogic()

    init {
        if (calculatorViewManager.getBinding() is ActivitySimpleCalculatorBinding) {
            binding = calculatorViewManager.getBinding() as ActivitySimpleCalculatorBinding
        }
    }

    protected fun handleDigitClick(digit: String) {
        if (calculatorViewManager.isNewOperation()) {
            calculatorViewManager.clearMainTextView()
            calculatorViewManager.setIsNewOperation(false)
        }
        calculatorViewManager.appendMainTextView(digit)
        calculatorViewManager.setIsEntryClearPressed(false)
    }

    protected fun getFirstOperand(): Double {
        return firstOperand
    }

    protected fun handleOperatorClick(operator: String) {
        if (calculatorViewManager.isInitState() && calculatorViewManager.isMainTextViewEmpty()) {
            return
        }

        if (calculatorViewManager.isOperatorInserted()) {
            if (calculatorViewManager.isNewOperation()) { // replace operator
                val currentOperator = calculatorViewManager.getCurrentOperator()
                calculatorViewManager.setOperator(
                    currentOperator.replace(
                        currentOperator.last(),
                        operator[0]
                    )
                )

            } else { // evaluate expression and insert new operator
                handleEqualsClick()
                insertOperator(operator)
                firstOperand = calculatorViewManager.getCurrentMainText().toDouble()
            }
        } else {
            insertOperator(operator)
            clearAndSaveOperand()
        }
    }

    private fun insertOperator(operator: String) {
        calculatorViewManager.setOperator(operator)

        calculatorViewManager.setIsInitState(false)
        calculatorViewManager.setIsNewOperation(true)
    }

    protected fun clearAndSaveOperand() {
        if (calculatorViewManager.isMainTextViewEmpty()) {
            return
        }

        firstOperand = calculatorViewManager.getCurrentMainText().toDouble()
    }

    protected fun handleDecimalClick() {
        if (calculatorViewManager.isNewOperation()) {
            calculatorViewManager.setMainTextView("0.")
            calculatorViewManager.setIsNewOperation(false)
        }

        if (calculatorViewManager.getCurrentMainText().contains(".")) {
            return
        }

        calculatorViewManager.setIsEntryClearPressed(false)

        if (calculatorViewManager.isMainTextViewEmpty()) {
            calculatorViewManager.appendMainTextView("0.")
        } else {
            calculatorViewManager.appendMainTextView(".")
        }
    }

    protected fun handleBackSpaceClick() {
        if (calculatorViewManager.isMainTextViewEmpty()) {
            return
        }

        val mainText = calculatorViewManager.getCurrentMainText()

        calculatorViewManager.setMainTextView(
            mainText.substring(0, mainText.length - 1)
        )

        if (!calculatorViewManager.isMainTextViewEmpty() && mainText.last() == '.') {
            calculatorViewManager.setMainTextView(mainText.substring(0, mainText.length - 1))
        }

        if (calculatorViewManager.getCurrentMainText().isEmpty()) {
            if (calculatorViewManager.isNewOperation()) {
                clearAll()
            } else {
                clearEntry()
            }
        }
    }

    protected fun handleSignClick() {
        val currentText = calculatorViewManager.getCurrentMainText()
        if (currentText.isNotEmpty()) {
            val firstChar = currentText[0]
            if (firstChar == '-') {
                calculatorViewManager.setMainTextView(currentText.substring(1))
            } else {
                calculatorViewManager.setMainTextView("-$currentText")
            }
        }
    }

    protected fun handleEqualsClick() {
        if (calculatorViewManager.isMainTextViewEmpty()) {
            return
        }

        val secondOperand = calculatorViewManager.getCurrentMainText().toDouble()

        val result = calculatorLogic.evaluateExpression(
            firstOperand,
            secondOperand,
            calculatorViewManager.getCurrentOperator()
        )

        val resultStr = cutDecimalPartIfInteger(result)

        calculatorViewManager.setMainTextView(resultStr)

        firstOperand = result
        calculatorViewManager.setIsNewOperation(true)
        calculatorViewManager.clearOperator()
    }

    protected fun cutDecimalPartIfInteger(result: Double): String {
        return if (result % 1.0 == 0.0) result.toInt().toString() else result.toString()
    }

    protected fun clearAll() {
        calculatorViewManager.clearMainTextView()
        calculatorViewManager.clearOperator()
        calculatorViewManager.setIsInitState(true)
        calculatorViewManager.setIsNewOperation(false)
        calculatorViewManager.setIsEntryClearPressed(false)
        firstOperand = 0.0
    }

    protected fun clearEntry() {
        if (calculatorViewManager.isEntryClearPressed()) {
            clearAll()
            return
        }

        if (calculatorViewManager.isNewOperation()) {
            return
        }

        if (!calculatorViewManager.isMainTextViewEmpty()) {
            calculatorViewManager.clearMainTextView()
        }

        calculatorViewManager.setIsEntryClearPressed(true)
    }

    open fun setUpSimpleCalculatorListeners() {
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
            if (!calculatorViewManager.isMainTextViewEmpty() && !calculatorViewManager.isNewOperation()) {
                handleDigitClick("0")
            }
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
            clearEntry()
        }

        binding.decimalBtn.setOnClickListener {
            handleDecimalClick()
        }

        binding.signBtn.setOnClickListener {
            handleSignClick()
        }
    }

}
package com.example.calculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivitySimpleCalculatorBinding


interface BindingStrategy {
    fun setMainTextView(value: String)
    fun appendMainTextView(value: String)
    fun setOperator(value: String)
    fun isMainTextViewEmpty(): Boolean
    fun getCurrentMainText(): String
    fun getCurrentOperator(): String
    fun clearMainTextView()
    fun clearOperator()

}

class SimpleCalculatorViewManager(private val binding: ActivitySimpleCalculatorBinding) :
    BindingStrategy {
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
}

class SimpleCalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySimpleCalculatorBinding

    private var isOperatorInserted = false
    private var isInitState = true
    private var newOperationFlag = false
    private var isEntryCleared = false
    private var firstOperand: Double = 0.0

    private lateinit var calculatorViewManager: BindingStrategy

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySimpleCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        calculatorViewManager = SimpleCalculatorViewManager(binding)

        setUpListeners()
    }

    private fun handleDigitClick(digit: String) {
        if (newOperationFlag) {
            calculatorViewManager.clearMainTextView()
            newOperationFlag = false
        }
        calculatorViewManager.appendMainTextView(digit)
        isEntryCleared = false
    }

    private fun handleOperatorClick(operator: String) {
        if (isInitState && calculatorViewManager.isMainTextViewEmpty()) {
            return
        }

        if (isOperatorInserted) {
            if (newOperationFlag) { // replace operator
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

        isOperatorInserted = true
        isInitState = false
        newOperationFlag = true
    }

    private fun clearAndSaveOperand() {
        if (calculatorViewManager.isMainTextViewEmpty()) {
            return
        }

        firstOperand = calculatorViewManager.getCurrentMainText().toDouble()
    }

    private fun handleDecimalClick() {
        if (calculatorViewManager.getCurrentMainText().contains(".")) {
            return
        }

        isEntryCleared = false

        if (calculatorViewManager.isMainTextViewEmpty() || isOperatorInserted) {
            calculatorViewManager.appendMainTextView("0.")
        } else {
            calculatorViewManager.appendMainTextView(".")
        }
    }

    private fun handleBackSpaceClick() {
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
            if (newOperationFlag) {
                clearAll()
            } else {
                clearEntry()
            }
        }
    }

    private fun handleSignClick() {
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
        if (calculatorViewManager.isMainTextViewEmpty()) {
            return
        }

        val secondOperand = calculatorViewManager.getCurrentMainText().toDouble()

        val result = evaluateExpression(
            firstOperand,
            secondOperand,
            calculatorViewManager.getCurrentOperator()
        )

        if (result % 1.0 == 0.0) {
            calculatorViewManager.setMainTextView(result.toInt().toString())
        } else {
            calculatorViewManager.setMainTextView(result.toString())
        }

        firstOperand = result

        isOperatorInserted = false
        newOperationFlag = true
        calculatorViewManager.clearOperator()
    }

    private fun clearAll() {
        calculatorViewManager.clearMainTextView()
        calculatorViewManager.clearOperator()
        firstOperand = 0.0
        isOperatorInserted = false
        isInitState = true
        newOperationFlag = false
        isEntryCleared = false
    }

    private fun clearEntry() {
        if (isEntryCleared) {
            clearAll()
            return
        }

        if (newOperationFlag) {
            return
        }

        if (!calculatorViewManager.isMainTextViewEmpty()) {
            calculatorViewManager.clearMainTextView()
        }

        isEntryCleared = true
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
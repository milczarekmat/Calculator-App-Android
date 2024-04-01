package com.example.calculator.listener.advanced

import com.example.calculator.bindingControllers.ViewManagerStrategy
import com.example.calculator.databinding.ActivityAdvancedCalculatorBinding
import com.example.calculator.listener.simple.SimpleCalculatorClickListener
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.log10
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

class AdvancedCalculatorClickListener(private val calculatorViewManager: ViewManagerStrategy) :
    SimpleCalculatorClickListener(calculatorViewManager) {

    private val binding: ActivityAdvancedCalculatorBinding =
        calculatorViewManager.getBinding() as ActivityAdvancedCalculatorBinding

    override fun setUpSimpleCalculatorListeners() {
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

    fun setUpAdvancedCalculatorListeners() {
        binding.sinBtn.setOnClickListener {
            handleSinClick()
        }

        binding.cosBtn.setOnClickListener {
            handleCosClick()
        }

        binding.tanBtn.setOnClickListener {
            handleTanClick()
        }

        binding.logBtn.setOnClickListener {
            handleLogClick()
        }

        binding.lnBtn.setOnClickListener {
            handleLnClick()
        }

        binding.sqrtBtn.setOnClickListener {
            handleSqrtClick()
        }

        binding.percentBtn.setOnClickListener {
            handlePercentClick()
        }

        binding.squareBtn.setOnClickListener {
            handleSquareClick()
        }

        binding.powerBtn.setOnClickListener() {
            handlePowerClick()
        }
    }

    private fun handlePowerClick() {
        if (calculatorViewManager.isMainTextViewEmpty()) {
            return
        }

        clearAndSaveOperand()
        handleOperatorClick("${cutDecimalPartIfInteger(getFirstOperand())}^")
    }

    private fun handleSquareClick() {
        if (calculatorViewManager.isMainTextViewEmpty()) {
            return
        }

        val value = calculatorViewManager.getCurrentMainText().toDouble()
        val result = value * value

        calculatorViewManager.setMainTextView(result.toString())
    }

    private fun handlePercentClick() {
        if (calculatorViewManager.isMainTextViewEmpty()) {
            return
        }

        val value = calculatorViewManager.getCurrentMainText().toDouble()
        val result = value / 100

        calculatorViewManager.setMainTextView(result.toString())
    }

    private fun handleSqrtClick() {
        if (calculatorViewManager.isMainTextViewEmpty()) {
            return
        }

        val value = calculatorViewManager.getCurrentMainText().toDouble()
        val result = sqrt(value)

        calculatorViewManager.setMainTextView(result.toString())
    }

    private fun handleLnClick() {
        if (calculatorViewManager.isMainTextViewEmpty()) {
            return
        }

        val value = calculatorViewManager.getCurrentMainText().toDouble()
        val result = ln(value)

        calculatorViewManager.setMainTextView(result.toString())
    }

    private fun handleLogClick() {
        if (calculatorViewManager.isMainTextViewEmpty()) {
            return
        }

        val value = calculatorViewManager.getCurrentMainText().toDouble()
        val result = log10(value)

        calculatorViewManager.setMainTextView(result.toString())
    }

    private fun handleTanClick() {
        if (calculatorViewManager.isMainTextViewEmpty()) {
            return
        }

        val value = calculatorViewManager.getCurrentMainText().toDouble()
        val result = tan(value)

        calculatorViewManager.setMainTextView(result.toString())
    }

    private fun handleCosClick() {
        if (calculatorViewManager.isMainTextViewEmpty()) {
            return
        }

        val value = calculatorViewManager.getCurrentMainText().toDouble()
        val result = cos(value)

        calculatorViewManager.setMainTextView(result.toString())
    }

    private fun handleSinClick() {
        if (calculatorViewManager.isMainTextViewEmpty()) {
            return
        }

        val value = calculatorViewManager.getCurrentMainText().toDouble()
        val result = sin(value)

        calculatorViewManager.setMainTextView(result.toString())
    }

}
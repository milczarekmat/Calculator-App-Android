package com.example.calculator.listeners.advanced

import android.content.Context
import android.widget.Toast
import com.example.calculator.viewManagers.AbstractViewManager
import com.example.calculator.databinding.ActivityAdvancedCalculatorBinding
import com.example.calculator.listeners.simple.SimpleCalculatorClickListener
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.log10
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

class AdvancedCalculatorClickListener(
    private val calculatorViewManager: AbstractViewManager,
    private val context: Context
) :
    SimpleCalculatorClickListener(calculatorViewManager, context) {

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
//            if (!calculatorViewManager.isMainTextViewEmpty() && !calculatorViewManager.isNewOperation()) {
//                handleDigitClick("0")
//            }

            if (!calculatorViewManager.isMainTextViewEmpty() && calculatorViewManager.getCurrentMainText().length == 1 && calculatorViewManager.getCurrentMainText()
                    .last() == '0'
            )
                return@setOnClickListener

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

    private fun setResult(result: Double) {
        calculatorViewManager.setMainTextView(cutDecimalPartIfInteger(result))
    }

    private fun handlePowerClick() {
        if (calculatorViewManager.isMainTextViewEmpty()) {
            return
        }

        saveOperand()
        calculatorViewManager.clearMainTextView()
        calculatorViewManager.setIsInitState(false)
        handleOperatorClick("${cutDecimalPartIfInteger(getFirstOperand())}^")
    }

    private fun handleSquareClick() {
        if (calculatorViewManager.isMainTextViewEmpty()) {
            return
        }

        val value = calculatorViewManager.getCurrentMainText().toDouble()
        val result = value * value

        setResult(result)
    }

    private fun handlePercentClick() {
        if (calculatorViewManager.isMainTextViewEmpty()) {
            return
        }

        val value = calculatorViewManager.getCurrentMainText().toDouble()
        val result = value / 100

        setResult(result)
    }

    private fun handleSqrtClick() {
        if (calculatorViewManager.isMainTextViewEmpty()) {
            return
        }

        val value = calculatorViewManager.getCurrentMainText().toDouble()

        if (value < 0) {
            Toast.makeText(context, "Nie można pierwiastkować liczby ujemnej", Toast.LENGTH_SHORT)
                .show()
            return
        }
        val result = sqrt(value)

        setResult(result)
    }

    private fun handleLnClick() {
        if (calculatorViewManager.isMainTextViewEmpty()) {
            return
        }


        val value = calculatorViewManager.getCurrentMainText().toDouble()

        if (value <= 0) {
            Toast.makeText(context, "Nie mozna uzywac logarytmow dla liczby ujemnej", Toast.LENGTH_SHORT)
                .show()
            return
        }

        val result = ln(value)

        setResult(result)
    }

    private fun handleLogClick() {
        if (calculatorViewManager.isMainTextViewEmpty()) {
            return
        }

        val value = calculatorViewManager.getCurrentMainText().toDouble()
        if (value <= 0) {
            Toast.makeText(context, "Nie mozna uzywac logarytmow dla liczby ujemnej", Toast.LENGTH_SHORT)
                .show()
            return
        }

        val result = log10(value)

        setResult(result)
    }

    private fun handleTanClick() {
        if (calculatorViewManager.isMainTextViewEmpty()) {
            return
        }

        val value = calculatorViewManager.getCurrentMainText().toDouble()
        val result = tan(value)

        setResult(result)
    }

    private fun handleCosClick() {
        if (calculatorViewManager.isMainTextViewEmpty()) {
            return
        }

        val value = calculatorViewManager.getCurrentMainText().toDouble()
        val result = cos(value)

        setResult(result)
    }

    private fun handleSinClick() {
        if (calculatorViewManager.isMainTextViewEmpty()) {
            return
        }

        val value = calculatorViewManager.getCurrentMainText().toDouble()
        val result = sin(value)

        setResult(result)
    }

}
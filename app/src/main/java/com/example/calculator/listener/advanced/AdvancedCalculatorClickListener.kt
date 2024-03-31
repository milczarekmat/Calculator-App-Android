package com.example.calculator.listener.advanced

import com.example.calculator.bindingControllers.ViewManagerStrategy
import com.example.calculator.databinding.ActivityAdvancedCalculatorBinding
import com.example.calculator.listener.simple.SimpleCalculatorClickListener

class AdvancedCalculatorClickListener(private val calculatorViewManager: ViewManagerStrategy): SimpleCalculatorClickListener(calculatorViewManager) {

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
//        binding.sinBtn.setOnClickListener {
//            handleSinClick()
//        }
//
//        binding.cosBtn.setOnClickListener {
//            handleCosClick()
//        }
//
//        binding.tanBtn.setOnClickListener {
//            handleTanClick()
//        }
//
//        binding.logBtn.setOnClickListener {
//            handleLogClick()
//        }
//
//        binding.lnBtn.setOnClickListener {
//            handleLnClick()
//        }
//
//        binding.sqrtBtn.setOnClickListener {
//            handleSqrtClick()
//        }
//
//        binding.powBtn.setOnClickListener {
//            handlePowClick()
//        }
//
//        binding.piBtn.setOnClickListener {
//            handlePiClick()
//        }
//
//        binding.eBtn.setOnClickListener {
//            handleEClick()
//        }
    }

}
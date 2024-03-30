package com.example.calculator.binding

abstract class ViewManagerStrategy {
    private var isInitState: Boolean = true
    private var isNewOperation: Boolean = false
    private var isEntryClearPressed: Boolean = false

    abstract fun setMainTextView(value: String)
    abstract fun appendMainTextView(value: String)
    abstract fun setOperator(value: String)
    abstract fun isMainTextViewEmpty(): Boolean
    abstract fun getCurrentMainText(): String
    abstract fun getCurrentOperator(): String
    abstract fun clearMainTextView()
    abstract fun clearOperator()
    abstract fun isOperatorInserted(): Boolean

    fun isInitState(): Boolean {
        return isInitState
    }

    fun isNewOperation(): Boolean {
        return isNewOperation
    }

    fun isEntryClearPressed(): Boolean {
        return isEntryClearPressed
    }

    fun setIsInitState(value: Boolean) {
        isInitState = value
    }

    fun setIsNewOperation(value: Boolean) {
        isNewOperation = value
    }

    fun setIsEntryClearPressed(value: Boolean) {
        isEntryClearPressed = value
    }
}
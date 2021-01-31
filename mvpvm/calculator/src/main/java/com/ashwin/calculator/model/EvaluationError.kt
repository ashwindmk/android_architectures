package com.ashwin.calculator.model

const val VALIDATION_ERROR = "Invalid Expression."
const val COMPUTATION_ERROR = "Computation Failed."
const val UNKNOWN_ERROR = "Unknown Error."

sealed class EvaluationError(message: String? = null): Exception(message) {
    class ValidationError: EvaluationError(VALIDATION_ERROR)
    class CalculationError: EvaluationError(COMPUTATION_ERROR)
    class UnknownError: EvaluationError(UNKNOWN_ERROR)
}

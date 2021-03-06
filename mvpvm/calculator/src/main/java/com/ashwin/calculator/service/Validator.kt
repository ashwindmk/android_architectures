package com.ashwin.calculator.service

import com.ashwin.calculator.model.EvaluationError
import com.ashwin.calculator.model.EvaluationResult

object Validator {
    fun validate(expression: String): EvaluationResult<Exception, Boolean> {
        // check for valid starting/ending chars
        if (invalidStart(expression)) return EvaluationResult.build { throw EvaluationError.ValidationError() }
        if (invalidEnd(expression)) return EvaluationResult.build { throw EvaluationError.ValidationError() }

        // Check for concurrent decimals and operators like "2++2"
        if (hasConcurrentOperators(expression)) EvaluationResult.build { throw EvaluationError.ValidationError() }
        if (hasConcurrentDecimals(expression)) EvaluationResult.build { throw EvaluationError.ValidationError() }
        if (hasDecimalBeforeOperator(expression)) EvaluationResult.build { throw EvaluationError.ValidationError() }
        if (hasDecimalAfterOperator(expression)) EvaluationResult.build { throw EvaluationError.ValidationError() }

        return EvaluationResult.build { true }
    }

    private fun invalidStart(expression: String): Boolean {
        when {
            expression.startsWith("+") -> return true
            expression.startsWith("-") -> return true
            expression.startsWith("*") -> return true
            expression.startsWith("/") -> return true
            expression.startsWith(".") -> return true
            else -> return false
        }
    }

    private fun invalidEnd(expression: String): Boolean {
        when {
            expression.endsWith("+") -> return true
            expression.endsWith("-") -> return true
            expression.endsWith("*") -> return true
            expression.endsWith("/") -> return true
            expression.endsWith(".") -> return true
            else -> return false
        }
    }

    private fun hasConcurrentDecimals(expression: String): Boolean {
        expression.indices
            .forEach {
                if (it < expression.lastIndex) {
                    if (isConcurrentDecimal(expression[it], expression[it + 1])) {
                        return true
                    }
                }
            }
        return false
    }

    private fun isConcurrentDecimal(current: Char, next: Char): Boolean {
        if (current.toString() == "." && next.toString() == ".") {
            return true
        }
        return false
    }

    private fun hasConcurrentOperators(expression: String): Boolean {
        expression.indices
            .forEach {
                if (it < expression.lastIndex) {
                    if (isConcurrentOperator(expression[it], expression[it + 1])) {
                        return true
                    }
                }
            }

        return false
    }

    private fun isConcurrentOperator(current: Char, next: Char): Boolean {
        if (isOperator(current) && isOperator(next)) {
            return true
        }
        return false
    }

    private fun isOperator(char: Char): Boolean {
        return when {
            //not sure why I had to toString() but char.equals("+") was not working as expected
            char.toString() == "+" -> true
            char.toString() == "-" -> true
            char.toString() == "*" -> true
            char.toString() == "/" -> true
            else -> false
        }
    }

    private fun isOperatorAfterDecimal(current: Char, next: Char): Boolean {
        return if (current.toString() == "+" && next.toString() == ".") true
        else if (current.toString() == "-" && next.toString() == ".") true
        else if (current.toString() == "/" && next.toString() == ".") true
        else if (current.toString() == "*" && next.toString() == ".") true
        else false
    }

    private fun isDecimalAfterOperator(current: Char, next: Char): Boolean {
        return if (current.toString() == "." && next.toString() == "+") true
        else if (current.toString() == "." && next.toString() == "-") true
        else if (current.toString() == "." && next.toString() == "*") true
        else if (current.toString() == "." && next.toString() == "/") true
        else false
    }

    private fun hasDecimalBeforeOperator(expression: String): Boolean {
        expression.indices
            .forEach {
                if (it < expression.lastIndex) {
                    if (isOperatorAfterDecimal(expression[it], expression[it + 1])) {
                        return true
                    }
                }
            }
        return false
    }

    private fun hasDecimalAfterOperator(expression: String): Boolean {
        expression.indices
            .forEach {
                if (it < expression.lastIndex) {
                    if (isDecimalAfterOperator(expression[it], expression[it + 1])) {
                        return true
                    }
                }
            }
        return false
    }
}
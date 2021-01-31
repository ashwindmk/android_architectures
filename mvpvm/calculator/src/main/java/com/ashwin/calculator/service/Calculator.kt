package com.ashwin.calculator.service

import com.ashwin.calculator.model.Operand
import com.ashwin.calculator.model.Operator

class Calculator {
    fun calculate(expression: String): String {
        val operator: MutableList<Operator> = getOperators(expression)
        val operands: MutableList<Operand> = getOperands(expression)

        while (operands.size > 1) {
            val firstOperand = operands[0]
            val secondOperand = operands[1]
            val firstOperator = operator[0]

            //if op is * or / (evaluateFirst), or no more operatorDataModels to follow,
            // or next op is NOT (evaluateFirst)
            if (firstOperator.evaluateFirst
                    || operator.elementAtOrNull(1) == null
                    || !operator[1].evaluateFirst) {
                val result = Operand(evaluatePair(firstOperand, secondOperand, firstOperator))
                operator.remove(firstOperator)
                operands.remove(firstOperand)
                operands.remove(secondOperand)

                operands.add(0, result)
            } else {
                val thirdOperand = operands[2]
                val secondOperator = operator[1]
                val result = Operand(evaluatePair(secondOperand, thirdOperand, secondOperator))

                operator.remove(secondOperator)
                operands.remove(secondOperand)
                operands.remove(thirdOperand)

                operands.add(1, result)
            }
        }

        return operands[0].value
    }

    //@VisibleForTesting
    internal fun getOperands(expression: String): MutableList<Operand> {
        val operands = expression.split("+", "-", "/", "*")
        val outPut: MutableList<Operand> = arrayListOf()

        // Kotlin's enhanced for loop
        operands.indices.mapTo(outPut) {
            Operand(operands[it])
        }
        return outPut
    }

    internal fun getOperators(expression: String): MutableList<Operator> {
        // Split based on number or decimal numbers.
        val operators = expression.split("\\d+(?:\\.\\d+)?".toRegex())
            .filterNot { it == "" }
            .toMutableList()
        val outPut: MutableList<Operator> = arrayListOf()

        operators.indices.mapTo(outPut) {
            Operator(operators[it])
        }
        return outPut
    }

    internal fun evaluatePair(firstOperand: Operand, secondOperand: Operand, operator: Operator): String {
        when (operator.value) {
            "+" -> return (firstOperand.value.toFloat() + secondOperand.value.toFloat()).toString()
            "-" -> return (firstOperand.value.toFloat() - secondOperand.value.toFloat()).toString()
            "/" -> return (firstOperand.value.toFloat() / secondOperand.value.toFloat()).toString()
            "*" -> return (firstOperand.value.toFloat() * secondOperand.value.toFloat()).toString()
        }
        throw  IllegalArgumentException("Illegal Operator.")
    }
}

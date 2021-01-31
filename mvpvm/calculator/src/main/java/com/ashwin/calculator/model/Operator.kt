package com.ashwin.calculator.model

import java.lang.IllegalArgumentException

data class Operator(val value: String) {
    val evaluateFirst: Boolean = checkPriority(value)

    private fun checkPriority(v: String): Boolean {
        return when (v) {
            "*" -> true
            "/" -> true
            "+" -> false
            "-" -> false
            else -> throw  IllegalArgumentException("Illegal Operator.")
        }
    }
}

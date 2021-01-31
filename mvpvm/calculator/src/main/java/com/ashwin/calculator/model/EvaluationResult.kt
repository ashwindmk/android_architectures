package com.ashwin.calculator.model

import java.lang.Exception

sealed class EvaluationResult<out E, out V> {
    data class Value<out V>(val value: V) : EvaluationResult<Nothing, V>()
    data class Error<out E>(val error: E) : EvaluationResult<E, Nothing>()

    companion object {
        inline fun <V> build(function: () -> V): EvaluationResult<Exception, V> =
            try {
                Value(function.invoke())
            } catch (e: Exception) {
                Error(e)
            }
    }
}

package com.ashwin.calculator.usecase

import com.ashwin.calculator.model.EvaluationError
import com.ashwin.calculator.model.EvaluationResult
import com.ashwin.calculator.repository.ResultRepository
import com.ashwin.calculator.service.Calculator
import com.ashwin.calculator.service.Validator
import java.lang.Exception

class Evaluator(val service: Calculator, val resultRepository: ResultRepository) {
    fun evaluate(expression: String): EvaluationResult<Exception, String> {
        try {
            val savedResult = resultRepository.getResult(expression)
            return if (savedResult != null && savedResult.isNotBlank()) {
                EvaluationResult.build { savedResult }
            } else {
                // If result is not stored in repository
                return when (val validationResult = Validator.validate(expression)) {
                    is EvaluationResult.Value -> {
                        val result = service.calculate(expression)
                        //resultRepository.saveResult(expression, result)
                        EvaluationResult.build { result }
                    }
                    is EvaluationResult.Error -> validationResult
                }
            }
        } catch (e: Exception) {
            return EvaluationResult.build { throw EvaluationError.UnknownError() }
        }
    }
}

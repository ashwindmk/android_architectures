package com.ashwin.calculator

import com.ashwin.calculator.model.EvaluationResult
import com.ashwin.calculator.repository.ResultRepository
import com.ashwin.calculator.service.Calculator
import com.ashwin.calculator.usecase.Evaluator
import io.mockk.clearAllMocks
import io.mockk.mockk
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class EvaluatorTest {
    val SIMPLE_EXPRESSION = "2+2"
    val SIMPLE_ANSWER = "4.0"

    val COMPLEX_EXPRESSION = "2+2-1*3+4"
    val COMPLEX_ANSWER = "5.0"

    val calculator = Calculator()
    lateinit var resultRepository: ResultRepository

    @Before
    fun before() {
        resultRepository = mockk(relaxed = true)
    }

    @Test
    fun addition_isCorrect() {
        Assert.assertEquals(4, 2 + 2)
    }

    @Test
    fun simpleTest() {
        val evaluator = Evaluator(calculator, resultRepository)
        val expected = SIMPLE_ANSWER
        val actual = evaluator.evaluate(SIMPLE_EXPRESSION) as EvaluationResult.Value
        Assert.assertEquals(expected, actual.value)
    }

    @Test
    fun complexTest() {
        val evaluator = Evaluator(calculator, resultRepository)
        val expected = COMPLEX_ANSWER
        val actual = evaluator.evaluate(COMPLEX_EXPRESSION) as EvaluationResult.Value
        Assert.assertEquals(expected, actual.value)
    }

    @After
    fun after() {
        clearAllMocks()
    }
}
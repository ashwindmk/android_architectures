package com.ashwin.calculator

import com.ashwin.calculator.repository.ResultRepository
import com.ashwin.calculator.service.Calculator
import io.mockk.clearAllMocks
import io.mockk.mockk
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CalculatorTest {
    val SIMPLE_EXPRESSION = "2+2"
    val SIMPLE_ANSWER = "4.0"

    val COMPLEX_EXPRESSION = "2+2-1*3+4"
    val COMPLEX_ANSWER = "5.0"

    lateinit var resultRepository: ResultRepository

    @Before
    fun before() {
        resultRepository = mockk(relaxed = true)
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun simpleTest() {
        val calculator = Calculator()
        val expected = SIMPLE_ANSWER
        val actual = calculator.calculate(SIMPLE_EXPRESSION) //as EvaluationResult.Value
        assertEquals(expected, actual)
    }

    @Test
    fun complexTest() {
        val calculator = Calculator()
        val expected = COMPLEX_ANSWER
        val actual = calculator.calculate(COMPLEX_EXPRESSION) //as EvaluationResult.Value
        assertEquals(expected, actual)
    }

    @After
    fun after() {
        clearAllMocks()
    }
}
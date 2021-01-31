package com.ashwin.calculator.repository

interface ResultRepository {
    fun getResult(expression: String): String?
    fun saveResult(expression: String, result: String)
}

package com.ashwin.calculator.data

import com.ashwin.calculator.repository.ResultRepository

class ResultRepositoryImpl : ResultRepository {
    private val map = HashMap<String, String>()

    override fun getResult(expression: String): String? {
        return map[expression]
    }

    override fun saveResult(expression: String, result: String) {
        if (map.size < 25) {
            map[expression] = result
        }
    }
}

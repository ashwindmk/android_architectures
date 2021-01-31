package com.ashwin.calculator.android

import android.app.Application
import android.util.Log

class CalculatorApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d(Constant.DEBUG_TAG, "CalculatorApp: onCreate: $packageName")
    }
}
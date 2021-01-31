package com.ashwin.calculator.android.di

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

object DispatcherProvider {
    fun uiContext(): CoroutineContext {
        return Dispatchers.Main
    }

    fun ioContext(): CoroutineContext {
        return Dispatchers.IO
    }
}

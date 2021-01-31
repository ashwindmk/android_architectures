package com.ashwin.calculator.android.di

import androidx.lifecycle.ViewModelProvider
import com.ashwin.calculator.android.main.MainContract
import com.ashwin.calculator.android.main.MainPresenter
import com.ashwin.calculator.android.main.MainViewModel
import com.ashwin.calculator.data.ResultRepositoryImpl
import com.ashwin.calculator.service.Calculator
import com.ashwin.calculator.usecase.Evaluator

object MainInjector {
    fun provideMainPresenter(view: MainContract.View): MainPresenter {
        val viewModel = ViewModelProvider(view, MainViewModelFactory()).get(MainViewModel::class.java)
        val evaluator = Evaluator(Calculator(), ResultRepositoryImpl())
        return MainPresenter(view, viewModel, DispatcherProvider, evaluator)
    }
}
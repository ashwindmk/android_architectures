package com.ashwin.calculator.android.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ashwin.calculator.android.main.MainViewModel

class MainViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel() as T
    }
}

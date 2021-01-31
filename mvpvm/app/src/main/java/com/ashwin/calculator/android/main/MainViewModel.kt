package com.ashwin.calculator.android.main

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope

class MainViewModel(private val displayState: MutableLiveData<String> = MutableLiveData()) : ViewModel(), MainContract.ViewModel {
    override fun getScope(): CoroutineScope {
        return viewModelScope
    }

    override fun setDisplayState(result: String) {
        displayState.value = result
    }

    override fun setDisplayObserver(owner: LifecycleOwner, observer: Observer<String>) {
        displayState.observe(owner, observer)
    }

    override fun getDisplayState(): String {
        return  displayState.value ?: ""
    }
}
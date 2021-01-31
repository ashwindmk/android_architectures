package com.ashwin.calculator.android.main

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope

interface MainContract {
    interface View: ViewModelStoreOwner {
        fun getLifecycleOwner(): LifecycleOwner

        fun getExpression(): String
        fun setDisplay(value: String)
        fun showError(value: String)
        fun restartFeature()
        fun bindEventListener()
    }

    interface ViewModel {
        fun getScope(): CoroutineScope
        fun setDisplayState(result: String)
        fun setDisplayObserver(owner: LifecycleOwner, observer: Observer<String>)
        fun getDisplayState(): String
    }

    // Unit tests will be written for every event of the presenter
    interface Presenter {
        fun onEvent(event: ViewEvent<Input>)
    }
}

enum class Input (val value: Char) {
    ONE('1'),
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8'),
    NINE('9'),
    ZERO('0'),
    DECIMAL('.'),
    PLUS('+'),
    MINUS('-'),
    MULTIPLY('*'),
    DIVIDE('/')
}

sealed class ViewEvent<out T> {
    object OnStart : ViewEvent<Nothing>()
    object OnDestroy : ViewEvent<Nothing>()
    data class OnOperandClick<out Input>(val char: Input) : ViewEvent<Input>()
    data class OnOperatorClick<out Input>(val char: Input) : ViewEvent<Input>()
    object OnEvaluateClick : ViewEvent<Nothing>()
    object OnDeleteClick : ViewEvent<Nothing>()
    object OnLongDeleteClick : ViewEvent<Nothing>()
}

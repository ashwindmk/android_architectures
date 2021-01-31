package com.ashwin.calculator.android.main

import android.util.Log
import androidx.lifecycle.Observer
import com.ashwin.calculator.android.Constant
import com.ashwin.calculator.android.di.DispatcherProvider
import com.ashwin.calculator.model.EvaluationResult
import com.ashwin.calculator.model.VALIDATION_ERROR
import com.ashwin.calculator.usecase.Evaluator
import kotlinx.coroutines.launch

class MainPresenter(private val view: MainContract.View,
                    private val viewModel: MainContract.ViewModel,
                    private val dispatcher: DispatcherProvider,
                    private val evaluator: Evaluator
        ) : MainContract.Presenter {
    override fun onEvent(event: ViewEvent<Input>) {
        when (event) {
            ViewEvent.OnStart -> onStart()
            is ViewEvent.OnOperandClick<Input> -> onOperandClick(event.char)
            is ViewEvent.OnOperatorClick<Input> -> onOperatorClick(event.char)
            ViewEvent.OnEvaluateClick -> onEvaluateClick()
            ViewEvent.OnDeleteClick -> onDeleteClick()
            ViewEvent.OnLongDeleteClick -> onLongDeleteClick()
            ViewEvent.OnDestroy -> onDestroy()
        }
    }

    private fun onStart() {
        view.bindEventListener()

        viewModel.setDisplayObserver(view.getLifecycleOwner(), Observer {
            Log.d(Constant.DEBUG_TAG, "MainView: update display: $it")
            view.setDisplay(it)
        })
    }

    private fun onOperandClick(char: Input) = viewModel.setDisplayState(viewModel.getDisplayState() + char.value)

    private fun onOperatorClick(char: Input) {
        val state = viewModel.getDisplayState()
        if (state != "") viewModel.setDisplayState(state + char.value)
        else showError(VALIDATION_ERROR)
    }

    private fun onEvaluateClick() {
        viewModel.getScope().launch(dispatcher.ioContext()) {
            val expression = viewModel.getDisplayState()
            val result = evaluator.evaluate(expression)
            Log.d(Constant.DEBUG_TAG, "onEvaluateClick: $expression = $result")

            viewModel.getScope().launch(dispatcher.uiContext()) {
                when (result) {
                    is EvaluationResult.Value -> {
                        viewModel.setDisplayState(result.value)
                    }
                    is EvaluationResult.Error -> showError(VALIDATION_ERROR)
                }
            }
        }
    }

    private fun onDeleteClick() {
        val state = viewModel.getDisplayState()
        if (state != "") viewModel.setDisplayState(state.dropLast(1))
    }

    private fun onLongDeleteClick() = viewModel.setDisplayState("")

    private fun showError(msg: String) {
        Log.d(Constant.DEBUG_TAG, "Error: $msg")
        view.showError(msg)
    }

    private fun onDestroy() {
        Log.d(Constant.DEBUG_TAG, "onDestroy")
    }
}